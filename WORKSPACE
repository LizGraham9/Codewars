workspace(name="codewars")

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "3.2"
RULES_JVM_EXTERNAL_SHA = "82262ff4223c5fda6fb7ff8bd63db8131b51b413d26eb49e3131037e79e324af"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

# maven_install will resolve all transitive dependencies.
maven_install(
    # If you update a dependency below execute this command:
    # bazel run @unpinned_maven//:pin
    artifacts = [
        # compact string form
        "junit:junit:4.13",
        # long form dependency definition allows for exclusion of
        # transitive dependencies.
        maven.artifact("org.hamcrest", "hamcrest", "2.2"),
        
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
    # after pinning your dependencies you can uncomment this
    # bazel run @maven//:pin
    maven_install_json = "@helloworld//:maven_install.json",
)

# used to provide the @unpinned_maven//:pin
load("@maven//:defs.bzl", "pinned_maven_install")
pinned_maven_install()