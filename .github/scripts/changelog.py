import os
import re
import subprocess
from datetime import datetime

def run_command(command):
    """Runs a shell command and returns the output."""
    print(f"Running command: {' '.join(command)}")
    result = subprocess.run(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    if result.returncode != 0:
        print(f"Command failed with error: {result.stderr}")
        exit(1)
    return result.stdout.strip()

def prepare_changelog(changelog_path, release_notes_path, tag):
    print(f"Preparing changelog with TAG: {tag}")
    
    if not os.path.isfile(changelog_path):
        print(f"Error: {changelog_path} not found.")
        exit(1)
        
    with open(changelog_path, 'r') as f:
        lines = f.readlines()
    
    print(f"Loaded {len(lines)} lines from {changelog_path}")
    insert_index = -1
    
    # Find where to insert the new tag section
    for i, line in enumerate(lines):
        print(f"Processing line {i}: {line.strip()}")
        if re.match(r"^## Unreleased", line):
            insert_index = i + 1
            break
    
    if insert_index == -1:
        print("Unreleased section not found in changelog.")
        exit(1)
    
    date = datetime.today().strftime("%Y-%m-%d")
    tag_url = f"https://github.com/rotgruengelb/maple4j/releases/tag/{tag}"
    new_section = f"\n## [{tag}]({tag_url}) - {date}\n"
    
    # Insert the new tag section
    lines.insert(insert_index, new_section)
    
    with open(changelog_path, 'w') as f:
        f.writelines(lines)
    
    print(f"Updated changelog written to {changelog_path}")

def get_change_log_notes(changelog_path, tag):
    print("Extracting changelog notes...")
    
    # Fetch tags from git
    run_command(["git", "fetch", "--tags"])
    all_tags = run_command(["git", "tag", "-l", "--sort=-version:refname", "v*"]).splitlines()
    print(f"All tags found: {', '.join(all_tags)}")
    
    # Read the changelog and find the notes for the current tag
    current_section_notes = []
    in_current_section = False
    last_tag = None
    
    with open(changelog_path, 'r') as f:
        for line in f:
            print(f"Reading line: {line.strip()}")
            if re.match(r"^## \[v", line):
                if in_current_section:
                    break  # End of current section
                if f"## [{tag}]" in line:
                    in_current_section = True
                    continue
            if in_current_section:
                current_section_notes.append(line)
    
    if not current_section_notes:
        print("No notes found in the current section")
        exit(1)
    
    notes = ''.join(current_section_notes).strip()
    print("Extracted notes:")
    print(notes)
    
    with open(release_notes_path, 'w') as f:
        f.write(notes)
    print(f"Notes written to {release_notes_path}")

def main():
    tag = os.getenv("TAG", "").lstrip("v")
    changelog_path = "CHANGELOG.md"
    release_notes_path = "RELEASE_NOTES.md"
    
    print(f"CHANGELOG_PATH: {changelog_path}")
    print(f"RELEASE_NOTES_PATH: {release_notes_path}")
    
    if not tag:
        print("TAG environment variable is missing or empty.")
        exit(1)
    
    prepare_changelog(changelog_path, release_notes_path, tag)
    get_change_log_notes(changelog_path, f"v{tag}")

if __name__ == "__main__":
    main()
