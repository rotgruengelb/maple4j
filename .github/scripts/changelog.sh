#!/bin/bash

set -e
set -x  # Enable command tracing

TAG=""
CHANGELOG_PATH=${CHANGELOG_PATH:-"CHANGELOG.md"}
RELEASE_NOTES_PATH="$(dirname "$CHANGELOG_PATH")/RELEASE_NOTES.md"

# Debug print paths
echo "CHANGELOG_PATH: $CHANGELOG_PATH"
echo "RELEASE_NOTES_PATH: $RELEASE_NOTES_PATH"

function run_command() {
    echo "Running command: $*"  # Debug print command
    "$@"
    if [ $? -ne 0 ]; then
        echo "Failed to run: $*" >> "$RELEASE_NOTES_PATH"
        exit 1
    fi
}

function prepare_changelog() {
    echo "Preparing changelog..."
    local changelog="$CHANGELOG_PATH"
    if [ ! -f "$changelog" ]; then
        echo "$changelog does not exist" >> "$RELEASE_NOTES_PATH"
        exit 1
    fi

    local insert_index=-1
    local lines=()
    mapfile -t lines < "$changelog"
    echo "Loaded ${#lines[@]} lines from $changelog"  # Debug print number of lines

    for i in "${!lines[@]}"; do
        local line="${lines[$i]}"
        echo "Processing line $i: $line"  # Debug print each line processed
        if [[ "$line" == "## Unreleased"* ]]; then
            insert_index=$((i + 1))
        elif [[ "$line" == "## [$TAG]"* ]]; then
            echo "CHANGELOG already up-to-date" >> "$RELEASE_NOTES_PATH"
            return
        elif [[ "$line" == "## [v"* ]]; then
            break
        fi
    done

    if [ $insert_index -lt 0 ]; then
        echo "Couldn't find 'Unreleased' section" >> "$RELEASE_NOTES_PATH"
        exit 1
    fi

    local date=$(date +'%Y-%m-%d')
    local tag_url="https://github.com/rotgruengelb/maple4j/releases/tag/$TAG"
    echo "Inserting tag $TAG with date $date at index $insert_index"  # Debug print tag insertion details

    lines=("${lines[@]:0:$insert_index}" \
           "" \
           "## [$TAG]($tag_url) - $date" \
           "${lines[@]:$insert_index}")

    printf "%s\n" "${lines[@]}" > "$changelog"
    echo "Updated changelog written to $changelog"  # Debug print after writing
}

function get_change_log_notes() {
    echo "Extracting change log notes..."
    local changelog="$CHANGELOG_PATH"
    local current_section_notes=()
    local in_current_section=false
    local last_tag=""

    # Get the last tag for comparison
    echo "Fetching tags..."
    run_command git fetch --tags

    local all_tags
    all_tags=$(git tag -l --sort=-version:refname 'v*')
    echo "All tags found: $all_tags"  # Debug print tags

    for tag_item in $all_tags; do
        echo "Checking tag: $tag_item"  # Debug print each tag checked
        if [[ -z "$tag_item" ]]; then
            continue
        fi
        if [[ "$tag_item" < "$TAG" ]]; then
            last_tag="$tag_item"
            echo "Previous tag found: $last_tag"  # Debug print last tag
            break
        fi
    done

    while IFS= read -r line; do
        echo "Reading line: $line"  # Debug print each line read
        if [[ "$line" == "## "* ]]; then
            if [[ "$line" == "## Unreleased"* ]]; then
                continue
            elif [[ "$line" == "## [$TAG]"* ]]; then
                in_current_section=true
                continue
            else
                break
            fi
        fi

        if $in_current_section; then
            current_section_notes+=("$line")
        fi
    done < "$changelog"

    if [ ${#current_section_notes[@]} -eq 0 ]; then
        echo "No notes found in the current section" >> "$RELEASE_NOTES_PATH"
        exit 1
    fi

    local compare_link=""
    if [ -n "$last_tag" ]; then
        compare_link="https://github.com/rotgruengelb/maple4j/compare/$last_tag...$TAG"
        current_section_notes+=("")
        current_section_notes+=("**[Full Changelog]($compare_link)**")
        echo "Compare link added: $compare_link"  # Debug print compare link
    fi

    {
        echo "## What's Changed"
        echo
        printf "%s\n" "${current_section_notes[@]}"
        echo
    } >> "$RELEASE_NOTES_PATH"
    echo "Change log notes written to $RELEASE_NOTES_PATH"  # Debug print after writing
}

function get_commit_history() {
    echo "Generating commit history..."
    local new_version="$TAG"

    run_command git fetch --tags

    local all_tags
    all_tags=$(git tag -l --sort=-version:refname 'v*')
    echo "All tags for commit history: $all_tags"  # Debug print tags for commit history

    local last_tag=""
    for tag_item in $all_tags; do
        echo "Checking tag for history: $tag_item"  # Debug print each tag checked
        if [[ -z "$tag_item" ]]; then
            continue
        fi
        if [[ "$tag_item" < "$new_version" ]]; then
            last_tag="$tag_item"
            echo "Last tag for comparison: $last_tag"  # Debug print last tag for comparison
            break
        fi
    done

    {
        echo "## Commit History"
        if [ -n "$last_tag" ]; then
            git log "$last_tag..$TAG" --oneline --first-parent
        else
            git log --oneline --first-parent
        fi
    } >> "$RELEASE_NOTES_PATH"
    echo "Commit history written to $RELEASE_NOTES_PATH"  # Debug print after writing
}

if [ "$#" -ne 1 ]; then
    echo "Usage: changelog.sh <version>" >> "$RELEASE_NOTES_PATH"
    echo "Error: version argument is missing"  # Debug print error
    exit 1
fi

TAG="v$1"
echo "Script started with TAG: $TAG"  # Debug print TAG value
prepare_changelog
get_change_log_notes
get_commit_history
echo "Script completed successfully."  # Debug print on completion
