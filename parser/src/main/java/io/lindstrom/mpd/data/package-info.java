@Value.Style(
        jdk9Collections = true,
        validationMethod = Value.Style.ValidationMethod.NONE,
        visibility = Value.Style.ImplementationVisibility.PACKAGE,
        builderVisibility = Value.Style.BuilderVisibility.PACKAGE,
        overshadowImplementation = true
)
package io.lindstrom.mpd.data;

import org.immutables.value.Value;