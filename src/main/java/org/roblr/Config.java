package org.roblr;

public class Config {

    /**
     *
     */
    public enum FieldVsClassResolutionPolicy {
        /**
         * Field-based generator always come winners,
         * even if there are multiple classes with the
         * same field name.
         */
        FIELD_WINS,

        /**
         * Class-based generators come out winners when there are
         * multiple classes with the same field name.
         */
        CLASS_WINS,

        /**
         * In case of multiple classes with same field name, throw an exception
         * when a conflict arises. This would force the user to specify
         * field-and-class based generator.
         */
        FORCE_SPECIFY;
    }

    private FieldVsClassResolutionPolicy fieldAlwaysBeatsClass;
    private int defaultIdLengthBytes = 4;

    /**
     * Specifies whether the fieldName-based generator is always more
     * specific than the ClassName - based even when there
     *
     * @return
     */
    public FieldVsClassResolutionPolicy isFieldAlwaysBeatsClass() {
        return fieldAlwaysBeatsClass;
    }

    public void setFieldAlwaysBeatsClass(FieldVsClassResolutionPolicy fieldAlwaysBeatsClass) {
        this.fieldAlwaysBeatsClass = fieldAlwaysBeatsClass;
    }

    public int getDefaultIdLengthBytes() {
        return defaultIdLengthBytes;
    }

    public void setDefaultIdLengthBytes(int defaultIdLengthBytes) {
        this.defaultIdLengthBytes = defaultIdLengthBytes;
    }

    public void validate() {
        if (defaultIdLengthBytes > 8)
            throw new IllegalArgumentException("defaultIdLengthBytes > 8 is not supported");
        if (defaultIdLengthBytes < 1)
            throw new IllegalArgumentException("defaultIdLengthBytes < 1 is not supported");

    }
}
