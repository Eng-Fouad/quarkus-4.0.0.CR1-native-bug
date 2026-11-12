# Quarkus 4.0.0.CR1: native build fails on Oracle GraalVM 25.0.4

A minimal reproducer: one `@QuarkusMain` class and `quarkus-arc`.

`main` uses Quarkus 4.0.0.Beta3, which builds natively with Oracle GraalVM 25.0.4. The pull request that upgrades it to
Quarkus 4.0.0.CR1 changes nothing else, and its native build with Oracle GraalVM 25.0.4 fails in the generated
`io.quarkus.runner.Feature`:

```
Error: Feature defined by io.quarkus.runner.Feature unexpectedly failed with a(n) java.lang.NoSuchMethodError.
Caused by: java.lang.NoSuchMethodError: 'void com.oracle.svm.hosted.FeatureImpl$DuringSetupAccessImpl.registerBuildTimeIndyIncludeList(java.lang.reflect.Executable)'
	at io.quarkus.runner.Feature.duringSetup(Unknown Source)
```

## Reproduce

With Oracle GraalVM 25.0.4 as `GRAALVM_HOME` (or `JAVA_HOME`):

```bash
./mvnw clean package -Dnative -Dquarkus.version=4.0.0.CR1
```

The [Native build](.github/workflows/native.yml) workflow runs two jobs, both with a local `native-image`:

| Job | Native image builder | Quarkus 4.0.0.Beta3 | Quarkus 4.0.0.CR1 |
|---|---|---|---|
| `oracle-graalvm` | Oracle GraalVM 25.0.4 (`graalvm/setup-graalvm`, `distribution: graalvm`) | works | fails |
| `mandrel` | Mandrel 25.0.4.0-Final (`graalvm/setup-graalvm`, `distribution: mandrel`) | works | works |
