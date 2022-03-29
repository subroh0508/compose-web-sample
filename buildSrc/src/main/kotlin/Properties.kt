import org.gradle.api.Project

internal fun Project.prop(propertyName: String) = property(propertyName) as String
internal fun Project.version(target: String) = prop("$target.version")
