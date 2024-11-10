package net.rotgruengelb.maple.internal;

import net.rotgruengelb.maple.exception.ExceptionPosition;
import net.rotgruengelb.maple.exception.MapleParseException;
import net.rotgruengelb.maple.exception.MapleSyntaxException;
import net.rotgruengelb.maple.internal.generated.MapleLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.IntervalSet;

import java.util.List;
import java.util.stream.IntStream;

public class MapleErrorListener extends BaseErrorListener {

	private static String getTokenName(Token token) {
		int tokenType = token != null ? token.getType() : -1;
		if (tokenType == MapleLexer.EOF) {
			return "<EOF>";
		}
		return token != null ? token.getText() : "<unknown>";
	}

	private static String getExpected(RecognitionException e) {
		IntervalSet expectedTokens = e.getExpectedTokens();
		return getExpected(expectedTokens);
	}

	private static String getExpected(IntervalSet expectedTokens) {
		List<String> sortedNames = expectedTokens.getIntervals()
				.stream()
				.flatMap(i -> IntStream.rangeClosed(i.a, i.b)
						.boxed())
				.flatMap(MapleTokenName::namesForToken)
				.sorted()
				.distinct()
				.map(MapleTokenName::displayName)
				.toList();

		if (sortedNames.isEmpty()) {
			return "a valid token";  // Fallback if no specific expected tokens are found
		}

		StringBuilder builder = new StringBuilder();
		int count = sortedNames.size();
		for (int i = 0; i < count; ++i) {
			builder.append(sortedNames.get(i));
			if (i < (count - 2)) {
				builder.append(", ");
			} else if (i == (count - 2)) {
				if (count >= 3) {
					builder.append(',');
				}
				builder.append(" or ");
			}
		}

		return builder.toString();
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPosition, String msg, RecognitionException e) {
		ExceptionPosition position = new ExceptionPosition(line, charPosition + 1);

		String message;
		if (e instanceof InputMismatchException || e instanceof NoViableAltException) {
			message = formatParserErrorMessage(
					(Token) offendingSymbol,
					e != null ? getExpected(e) : "a valid token",
					recognizer,
					line,
					charPosition
			);
		} else if (offendingSymbol instanceof Token && recognizer instanceof Parser) {
			message = formatParserErrorMessage(
					(Token) offendingSymbol,
					getExpected(((Parser) recognizer).getExpectedTokens()),
					recognizer,
					line,
					charPosition
			);
		} else if (offendingSymbol instanceof Token) {
			message = formatParserErrorMessage(
					(Token) offendingSymbol,
					"a valid token",
					recognizer,
					line,
					charPosition
			);
		} else {
			// For lexer (where token is not available)
			message = formatLexerErrorMessage(line, charPosition, msg, recognizer);
		}

		reportException(message, position, recognizer);
	}

	private void reportException(String msg, ExceptionPosition position, Recognizer<?, ?> recognizer) {
		if (recognizer instanceof Lexer) {
			throw new MapleSyntaxException(msg, position);
		} else {
			throw new MapleParseException(msg, position);
		}
	}

	private String formatParserErrorMessage(Token token, String expected, Recognizer<?, ?> recognizer, int line, int column) {
		String tokenName = getTokenName(token);
		String snippet = token != null ? getInputSnippet(token.getInputStream(),
				token.getStartIndex(), token.getStopIndex()) : "[No snippet available]";

		return String.format(
				"Unexpected token \"%s\" at line %d, column %d.\nExpected: %s.\n\n%s\n",
				tokenName, line, column + 1, expected, snippet
		);
	}

	private String formatLexerErrorMessage(int line, int column, String errorMsg, Recognizer<?, ?> recognizer) {
		String snippet = "[No snippet available]";
		String offendingSymbol = "<unknown>";
		if (recognizer instanceof Lexer lexer) {
			CharStream input = lexer.getInputStream();
			snippet = getInputSnippet(input, lexer._tokenStartCharIndex, lexer._input.index());
			offendingSymbol = input.getText(new Interval(lexer._tokenStartCharIndex, lexer._input.index()));
		}

		return String.format(
				"Invalid token \"%s\" at line %d, column %d.\n\n%s\n",
				offendingSymbol, line, column + 1, snippet
		);
	}

	private String getInputSnippet(CharStream input, int start, int stop) {
		int snippetStart = Math.max(0, start - 20);
		int snippetEnd = Math.min(input.size(), stop + 20);
		String snippet = input.getText(new Interval(snippetStart, snippetEnd));

		return snippet.replaceAll("\r?\n", " ") + // Avoid newlines in snippet
				"\n" + " ".repeat(Math.max(0, (start - snippetStart))) + "^";
	}
}
