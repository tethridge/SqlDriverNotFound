# SqlDriverNotFound
Minimal application to demonstrate error found when trying to use SqlDelight with KMP 
Multiplatform Compose app.

In this sample, the only error is for the SqlSchema.  However, in my project I encountered
an issue with both the SqlDriver and SqlSchema.

The errors look like:

```
Unresolved reference: SqlDriver
Cannot access class 'app.cash.sqldelight.db.SqlSchema'. Check your module classpath for missing or conflicting dependencies
Cannot access class 'app.cash.sqldelight.db.SqlDriver'. Check your module classpath for missing or conflicting dependencies
```

This project was created using the Kotlin Multiplatform Wizard to generate a 
minimal project structure (https://kmp.jetbrains.com/).

Then entries for sqldelight were added to libs.versions.toml and the corresponding
entries were added to composeApp:build.gradle.kts.

The only other changes were to update build.gradle.kts to use a jvm target of 17 and 
"implementation" was changed to "api" for compose.runtime under commonMain to get past
a compile error.

To duplicate the error run:

```
$ ./gradlew :composeApp:clean
$ ./gradlew :composeApp:assemble
```