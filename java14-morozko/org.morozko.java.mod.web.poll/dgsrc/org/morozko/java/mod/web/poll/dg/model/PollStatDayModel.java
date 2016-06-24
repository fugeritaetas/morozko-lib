/*
 * @(#)PollStatDayModel.java
 *
 * @project    : Morozko - Pool
 * @package    : org.morozko.java.mod.web.poll.dg.model
 * @creation   : 19/11/2009 10/40/59
 */
package org.morozko.java.mod.web.poll.dg.model;

import org.morozko.java.mod.web.poll.dg.model.helpers.PollStatDayModelHelper;
import org.morozko.java.mod.web.poll.facade.PollStat;

/**
 * <p>Classe PollStatDayModel.</p>
 *
 * @author Matteo Franci
 */
public class PollStatDayModel extends PollStatDayModelHelper implements PollStat {

	public String getAnswerNumberBean() {
		return this.getBean().getAnswerNumber();
	}

	private final static long serialVersionUID = 125862365952236L;

}
