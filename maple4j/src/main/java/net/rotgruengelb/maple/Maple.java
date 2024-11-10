package net.rotgruengelb.maple;

import net.rotgruengelb.maple.internal.MapleParse;
import net.rotgruengelb.maple.value.MapleMap;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.IntStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Maple {

	/**
	 * Parse a Maple string.
	 *
	 * @param input The input to parse.
	 * @return The parse result.
	 */
	public static MapleMap parse(String input) {
		return MapleParse.parse(CharStreams.fromString(input));
	}

	/**
	 * Parse a Maple file.
	 *
	 * @param file The input file to parse.
	 * @return The parse result.
	 * @throws IOException If an IO exception occurs.
	 */
	public static MapleMap parse(Path file) throws IOException {
		return parse(inputStreamReader(Files.newInputStream(file)));
	}

	/**
	 * Parse a Maple input stream.
	 *
	 * @param stream UTF-8 encoded input stream to read from.
	 * @return The parse result.
	 * @throws IOException If an IO exception occurs.
	 */
	public static MapleMap parse(InputStream stream) throws IOException {
		return parse(inputStreamReader(stream));
	}

	/**
	 * Parse a Maple input stream.
	 *
	 * @param reader The reader to obtain the Maple document from.
	 * @return The parse result.
	 * @throws IOException If an IO exception occurs.
	 */
	public static MapleMap parse(Reader reader) throws IOException {
		return MapleParse.parse(CharStreams.fromReader(reader));
	}

	/**
	 * Parse a Maple input stream.
	 *
	 * @param channel The UTF-8 encoded channel to read the Maple document from.
	 * @return The parse result.
	 * @throws IOException If an IO exception occurs.
	 */
	public static MapleMap parse(ReadableByteChannel channel) throws IOException {
		return MapleParse.parse(CharStreams.fromChannel(channel, StandardCharsets.UTF_8, 4096, CodingErrorAction.REPORT, IntStream.UNKNOWN_SOURCE_NAME, -1));
	}

	private static InputStreamReader inputStreamReader(InputStream stream) {
		return new InputStreamReader(stream, StandardCharsets.UTF_8.newDecoder()
				.onMalformedInput(CodingErrorAction.REPORT)
				.onUnmappableCharacter(CodingErrorAction.REPORT));
	}
}
