package org.fugerit.java.core.lang.helpers;
/*
 * @(#)MuteExHandler.java
 *
 * @project     : org.fugerit.java.core
 * @package     : org.fugerit.java.core.lang.helpers
 * @creation	: 6-dic-2004 12.41.55
 * @release		: xxxx.xx.xx
 */

/**
 * <p>Implementazione do-nothing di ExHandler.</p>
 * 
 * @author  Matteo Franci a.k.a. Fugerit
 */
public class MuteExHandler implements ExHandler {

    public static final ExHandler MUTE_HANDLER = new MuteExHandler();
    
    /* (non-Javadoc)
     * @see org.fugerit.java.core.lang.ExHandler#fatal(java.lang.Exception)
     */
    public void fatal(Exception e) {
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.lang.ExHandler#error(java.lang.Exception)
     */
    public void error(Exception e) {
    }

    /* (non-Javadoc)
     * @see org.fugerit.java.core.lang.ExHandler#warning(java.lang.Exception)
     */
    public void warning(Exception e) {
    }

}
