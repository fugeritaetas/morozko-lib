package org.morozko.java.mod.web.poll.facade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.morozko.java.mod.web.poll.dg.model.PollAnswerModel;
import org.morozko.java.mod.web.poll.dg.model.PollDataModel;
import org.morozko.java.mod.web.poll.dg.model.PollIndexModel;
import org.morozko.java.mod.web.poll.dg.model.PollOptionModel;
import org.morozko.java.mod.web.poll.dg.model.PollQuestionModel;

public class PollModel {

	public PollModel() {
		this.answerMap = new HashMap<String, PollAnswerModel>();
		this.optionMap = new HashMap<String, PollOptionModel>();
		this.statMap = new HashMap<String, PollStat>();
	}
	
	private PollIndexModel pollIndexModel;

	public PollIndexModel getPollIndexModel() {
		return pollIndexModel;
	}

	public void setPollIndexModel(PollIndexModel pollIndexModel) {
		this.pollIndexModel = pollIndexModel;
	}
	
	private PollDataModel pollDataModel;

	public PollDataModel getPollDataModel() {
		return pollDataModel;
	}

	public void setPollDataModel(PollDataModel pollDataModel) {
		this.pollDataModel = pollDataModel;
	}

	public void populateAnswerMap() {
		this.answerMap.clear();
		Iterator<PollAnswerModel> itAnswer = this.getPollDataModel().getListPollAnswer().iterator();
		while ( itAnswer.hasNext() ) {
			PollAnswerModel answerModel = itAnswer.next();
			this.answerMap.put( answerModel.getIdPollOption().toString() , answerModel );
		}
	}
	
	public void populateOptionMap( PollQuestionModel questionModel ) {
			Iterator<PollOptionModel> itOption = questionModel.getListPollOption().iterator();
			while ( itOption.hasNext() ) {
				PollOptionModel optionModel = itOption.next();
				this.optionMap.put( optionModel.getIdPollOption().toString() , optionModel );
			}
	}
	
	public void populateOptionMap() {
		this.optionMap.clear();
		Iterator<PollQuestionModel> itQuestion = this.getPollIndexModel().getListPollQuestion().iterator();
		while ( itQuestion.hasNext() ) {
			populateOptionMap( itQuestion.next() );
		}
	}
	
	private Map<String, PollStat> statMap;
	
	private Map<String, PollAnswerModel> answerMap;
	
	private Map<String, PollOptionModel> optionMap;

	public Map<String, PollAnswerModel> getAnswerMap() {
		return answerMap;
	}

	public Map<String, PollOptionModel> getOptionMap() {
		return optionMap;
	}

	public Map<String, PollStat> getStatMap() {
		return statMap;
	}
	
}
