@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface I18nExcelHeader {
    /**
     * The key used to look up the internationalized title for the Excel header.
     * @return the key for the internationalized title
     */
    String key() default "fond no title";
}