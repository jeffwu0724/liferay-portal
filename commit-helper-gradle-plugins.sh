cd modules/sdk/ant-bnd
../../../gradlew clean jar installCache updateFileVersions
git add "../../../*.gradle"
git commit -m "LPD-X Use latest"

cd ../gradle-plugins-baseline
../../../gradlew clean jar installCache updateFileVersions
git add "../../../*.md"
git add "../../../*.gradle"
git commit --amend --no-edit

cd ../gradle-plugins-jasper-jspc
../../../gradlew clean jar installCache updateFileVersions
git add "../../../*.md"
git add "../../../*.gradle"
git commit --amend --no-edit

cd ../gradle-plugins
../../../gradlew clean jar installCache updateFileVersions
git add "../../../*.gradle"
git commit --amend --no-edit

cd ../gradle-plugins-defaults
../../../gradlew clean jar installCache updateFileVersions
git add "../../../*.gradle"
git commit --amend --no-edit

cd ../../..

git add ".m2-tmp/*.jar"
git add ".m2-tmp/*.pom"
git commit -m "LPD-X Fake gradle cache"