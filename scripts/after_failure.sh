#!/bin/bash
# This script will update the Build status

DISTELLI_USERNAME=ipcrm
DISTELLI_API_URL="https://api.distelli.com/$DISTELLI_USERNAME"
DISTELLI_APP_NAME="sparkjava-puppet-webapp"
DISTELLI_TMP_RELEASE="DISTELLIrelease.$JOB_NAME.$BUILD_NUMBER.tmp"

# Setting global Distelli Env Variables
echo -e "\nSetting global Distelli environment variables\n"
DISTELLI_CI_PROVIDER="jenkins"
DISTELLI_BUILD_STATUS="Failed"

# Preparing to update the build event with "Failed" and build end time
echo -e "\nPreparing to update build event in Distelli\n"
DISTELLI_NOW=$(date -u +%Y-%m-%dT%H:%M:%S.0Z)
DISTELLI_TMP_FILENAME="DISTELLI.$JOB_NAME.$BUILD_NUMBER.tmp"
DISTELLI_BUILD_EVENT_ID=$(cat "$DISTELLI_TMP_FILENAME")
DISTELLI_RESPONSE=$(rm "$DISTELLI_TMP_FILENAME")

echo -e "Updating build event in Distelli\n"
# Updating build event with "Success" and build end time
API_JSON=$(printf '{"build_status":%s, "build_end":%s}' \
    "$(jq -R . <<<"$DISTELLI_BUILD_STATUS")" \
      "$(jq -R . <<<"$DISTELLI_NOW")")

DISTELLI_RESPONSE=$(curl -s -k -H "Content-Type: application/json" \
    -X POST "$DISTELLI_API_URL/apps/$DISTELLI_APP_NAME/events/$DISTELLI_BUILD_EVENT_ID?apiToken=$PIPELINES_API_TOKEN" \
      -d "$API_JSON")

echo -e "Distelli Build Update Response:\n $DISTELLI_RESPONSE\n\n"
