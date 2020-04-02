package com.orange.bscs.api.exceptions.factory;

/**
 * Snigleton returning registered IFaultFactory instance.
 * <p>
 * Default factory is FaultFactorySimple, but, with WebService development a
 * more specific Factory can be develop and registered <br/>
 * </p>
 * 
 * @see com.orange.bscs.api.model.exception.FaultFactorySimple.
 * 
 * @author IT&Labs
 * 
 */
public final class FaultFactory {

    private static IFaultFactory factory = new FaultFactorySimple();

    /** Hidden utility class constructor */
    private FaultFactory() {
    }

    /**
     * @return current IFaultFactory registered in this singleton.
     *         (FaultFactorySimple by default)
     */
    public static IFaultFactory getInstance() {
        return factory;
    }

    /**
     * Change implementation
     * 
     * use this declaration : <code>
     *  <!--  Static initializer -->
     * <bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
     *     <property name="staticMethod" value="com.orange.bscs.api.model.exception.FaultFactory.setInstance"/>
     *     <property name="arguments">
     *       <bean class="com.orange.bscs.api.model.exception.FaultFactoryBscsv1"></bean>
     *     </property>
     * </bean>
     * </code>
     * 
     * @param factory
     *            implementation to inject in static instance.
     * @return a new FaultFactory which can be ignore.
     */
    public static void setInstance(final IFaultFactory factory) {
        FaultFactory.factory = factory;
    }

}
