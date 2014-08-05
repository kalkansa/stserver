/**
 * @author 	Jadic
 * @created 2014-2-21
 */
package com.jadic.db;

public final class SQL {

    private SQL() {
    }
    
    public final static String QUERY_TERMINAL_INFO = "select a.id, a.enabled, -1 as channelId " +
    		                                           "from tab_terminal a " +
    		                                           "where a.typeid=1";
    public final static String UPDATE_TERMINAL_OFFLINE = "update tab_terminal_status " +
    		                                               "set OnlineStatus = 2 " +
    		                                               "where terminalId = ? ";
}
