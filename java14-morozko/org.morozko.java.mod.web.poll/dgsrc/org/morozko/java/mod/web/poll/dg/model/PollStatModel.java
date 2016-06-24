/*
 * @(#)PollStatModel.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model
 * @creation   : 19/11/2009 09/22/08
 */
package org.morozko.java.mod.web.poll.dg.model;

import org.morozko.java.mod.web.poll.dg.model.helpers.PollStatModelHelper;
import org.morozko.java.mod.web.poll.facade.PollStat;

/**
 * <p>Classe PollStatModel.</p>
 *
 * @author Matteo Franci
 */
public class PollStatModel extends PollStatModelHelper implements PollStat {

	public String getAnswerNumberBean() {
		return this.getBean().getAnswerNumber();
	}	
	
	private final static long serialVersionUID = 125861892843849L;

}
