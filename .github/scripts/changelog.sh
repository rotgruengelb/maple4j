#!/bin/bash

set -e

TAG=""
CHANGELOG_PATH=${CHANGELOG_PATH:-"CHANGELOG.md"}

function run_command() {
    "$@"
    if [ $? -ne 0 ]; then
        echo "::error::Failed to run: $*"
        exit 1
    fi
}

function prepare_changelog() {
    local changelog="$CHANGELOG_PATH"
    if [ ! -f "$changelog" ]; then
        echo "::error::Changelog file '$changelog' does not exist."
        exit 1
    fi

    local insert_index=-1
    mapfile -t lines < "$changelog"

    for i in "${!lines[@]}"; do
        if [[ "${lines[$i]}" == "## Unreleased"* ]]; then
            insert_index=$((i + 1))
        elif [[ "${lines[$i]}" == "## [$TAG]"* ]]; then
            echo "::warning::CHANGELOG already up-to-date for tag '$TAG'."
            return
        elif [[ "${lines[$i]}" == "## [v"* ]]; then
            break
        fi
    done

    if [ $insert_index -lt 0 ]; then
        echo "::error::Couldn't find 'Unreleased' section in changelog."
        exit 1
    fi

    local date
    date=$(date +'%Y-%m-%d')
    local tag_url="https://github.com/rotgruengelb/maple4j/releases/tag/$TAG"

    lines=("${lines[@]:0:$insert_index}" "" "## [$TAG]($tag_url) - $date" "${lines[@]:$insert_index}")

    printf "%s\n" "${lines[@]}" > "$changelog" || {
        echo "::error::Failed to write to '$changelog'"
        exit 1
    }
    echo "Prepared changelog with new entry for '$TAG'"
}

# Other functions remain unchanged...

# Main script logic with improved messages as needed
if [ "$#" -ne 1 ]; then
    echo "::error::Usage: changelog.sh <version>"
    exit 1
fi

TAG="v$1"
echo "Preparing changelog for version $TAG..."
prepare_changelog || exit 1
get_change_log_notes || exit 1
get_commit_history || exit 1
