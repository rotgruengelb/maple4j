#!/bin/bash

set -e

TAG=""
CHANGELOG_PATH=${CHANGELOG_PATH:-"CHANGELOG.md"}

function run_command() {
    "$@"
    if [ $? -ne 0 ]; then
        echo "Failed to run: $*"
        exit 1
    fi
}

function prepare_changelog() {
    local changelog="$CHANGELOG_PATH"
    if [ ! -f "$changelog" ]; then
        echo "$changelog does not exist"
        exit 1
    fi

    local insert_index=-1
    local lines=()
    mapfile -t lines < "$changelog"

    for i in "${!lines[@]}"; do
        local line="${lines[$i]}"
        if [[ "$line" == "## Unreleased"* ]]; then
            insert_index=$((i + 1))
        elif [[ "$line" == "## [$TAG]"* ]]; then
            echo "CHANGELOG already up-to-date"
            return
        elif [[ "$line" == "## [v"* ]]; then
            break
        fi
    done

    if [ $insert_index -lt 0 ]; then
        echo "Couldn't find 'Unreleased' section"
        exit 1
    fi

    local date=$(date +'%Y-%m-%d')
    local tag_url="https://github.com/rotgruengelb/maple4j/releases/tag/$TAG"

    lines=("${lines[@]:0:$insert_index}" \
           "" \
           "## [$TAG]($tag_url) - $date" \
           "${lines[@]:$insert_index}")

    printf "%s\n" "${lines[@]}" > "$changelog"
}

function get_change_log_notes() {
    local changelog="$CHANGELOG_PATH"
    local current_section_notes=()
    local in_current_section=false
    local last_tag=""

    # Get the last tag for comparison
    run_command git fetch --tags

    local all_tags
    all_tags=$(git tag -l --sort=-version:refname 'v*')

    for tag_item in $all_tags; do
        if [[ -z "$tag_item" ]]; then
            continue
        fi
        if [[ "$tag_item" < "$TAG" ]]; then
            last_tag="$tag_item"
            break
        fi
    done

    while IFS= read -r line; do
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
        echo "No notes found in the current section"
        exit 1
    fi

    # Create a comparison link if there's a previous tag
    local compare_link=""
    if [ -n "$last_tag" ]; then
        compare_link="https://github.com/rotgruengelb/maple4j/compare/$last_tag...$TAG"
        current_section_notes+=("")
        current_section_notes+=("**[Full Changelog]($compare_link)**")
    fi

    echo -e "## What's Changed\n\n${current_section_notes[*]}\n"
}

function get_commit_history() {
    local new_version="$TAG"

    run_command git fetch --tags

    local all_tags
    all_tags=$(git tag -l --sort=-version:refname 'v*')

    local last_tag=""
    for tag_item in $all_tags; do
        if [[ -z "$tag_item" ]]; then
            continue
        fi
        if [[ "$tag_item" < "$new_version" ]]; then
            last_tag="$tag_item"
            break
        fi
    done

    if [ -n "$last_tag" ]; then
        run_command git log "$last_tag..$TAG" --oneline --first-parent
    else
        run_command git log --oneline --first-parent
    fi
}

if [ "$#" -ne 1 ]; then
    echo "Usage: changelog.sh <version>"
    exit 1
fi

TAG="v$1"
prepare_changelog
get_change_log_notes
get_commit_history
