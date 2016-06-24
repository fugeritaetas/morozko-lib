package org.morozko.java.mod.web.poll.facade;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.morozko.java.core.ent.servlet.request.ParamMap;
import org.morozko.java.mod.db.dao.DAOID;
import org.morozko.java.mod.db.dao.OpDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollAnswerDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollDAOFactory;
import org.morozko.java.mod.web.poll.dg.dao.PollDataDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollIndexDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollOptionDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollQuestionDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollStatDAO;
import org.morozko.java.mod.web.poll.dg.dao.PollStatDayDAO;
import org.morozko.java.mod.web.poll.dg.model.PollAnswerModel;
import org.morozko.java.mod.web.poll.dg.model.PollDataModel;
import org.morozko.java.mod.web.poll.dg.model.PollIndexModel;
import org.morozko.java.mod.web.poll.dg.model.PollOptionModel;
import org.morozko.java.mod.web.poll.dg.model.PollQuestionModel;
import org.morozko.java.mod.web.poll.dg.model.PollStatDayModel;
import org.morozko.java.mod.web.poll.dg.model.PollStatModel;

public class PollFacade {
	
	public static void loadStat( PollModel pollModel ) throws Exception {
		PollStatDAO statDAO = PollDAOFactory.getInstance().getPollStatDAO();
		List<PollStatModel> list = statDAO.loadAllByPoll( pollModel.getPollIndexModel().getIdPollIndex() );
		Iterator<PollStatModel> it = list.iterator();
		while ( it.hasNext() ) {
			PollStatModel statModel = it.next();
			pollModel.getStatMap().put( statModel.getIdPollOption().toString() , statModel );
		}
	}
	
	public static void loadStatDay( PollModel pollModel, String day ) throws Exception {
		PollStatDayDAO statDAO = PollDAOFactory.getInstance().getPollStatDayDAO();
		List<PollStatDayModel> list = statDAO.loadAllByPollAndDate( pollModel.getPollIndexModel().getIdPollIndex(), day );
		Iterator<PollStatDayModel> it = list.iterator();
		while ( it.hasNext() ) {
			PollStatDayModel statModel = it.next();
			pollModel.getStatMap().put( statModel.getIdPollOption().toString() , statModel );
		}
	}	
	
	public static boolean populate( PollModel pollModel, ParamMap map ) {
		boolean ok = true;
		PollIndexModel indexModel = pollModel.getPollIndexModel();
		List<PollQuestionModel> listQuestion = indexModel.getListPollQuestion();
		Iterator<PollQuestionModel> itQuestion = listQuestion.iterator();
		PollDataModel dataModel = new PollDataModel();
		dataModel.setListPollAnswer( new ArrayList<PollAnswerModel>() );
		dataModel.setIdPollIndex( indexModel.getIdPollIndex() );
		while ( itQuestion.hasNext() && ok ) {
			PollQuestionModel questionModel = itQuestion.next();
			List<PollOptionModel> listOption = questionModel.getListPollOption();
			String paramQuestion = "question_"+questionModel.getIdPollQuestion();
			String[] paramValue = map.getParams( paramQuestion );
			if ( paramValue != null ) {
				String id = paramValue[0];
				PollAnswerModel answerModel = new PollAnswerModel();
				answerModel.setIdPollOption( DAOID.valueOf( id ) );
				dataModel.getListPollAnswer().add( answerModel );
				String paramKids =  "question_kids_"+questionModel.getIdPollQuestion();
				String[] paramKidsValue = map.getParams( paramKids );
				if ( paramKidsValue != null ) {
					for ( int k=0; k<paramKidsValue.length; k++ ) {
						String current = paramKidsValue[k];
						PollAnswerModel answerKidModel = new PollAnswerModel();
						answerKidModel.setIdPollOption( DAOID.valueOf( current ) );
						dataModel.getListPollAnswer().add( answerKidModel );
					}
				}
			} else { 
				ok = false;
			}
		}
		pollModel.setPollDataModel( dataModel );
		pollModel.populateAnswerMap();
		return ok;
	}
	
	public static boolean saveData( PollDataModel dataModel ) throws Exception {
		PollDAOFactory daoFactory = PollDAOFactory.getInstance();
		PollDataDAO dataDAO = daoFactory.getPollDataDAO();
		PollAnswerDAO answerDAO = daoFactory.getPollAnswerDAO();
		dataModel.setIdPollData( dataDAO.generateId() );
		dataModel.setDataTime( new Timestamp( System.currentTimeMillis() ) );
		Iterator<PollAnswerModel> itAnswer = dataModel.getListPollAnswer().iterator();
		List<OpDAO> listOp = new ArrayList<OpDAO>();
		listOp.add( dataDAO.newInsertOpDAO( dataModel ) );
		while ( itAnswer.hasNext() ) {
			PollAnswerModel answerModel = itAnswer.next();
			answerModel.setIdPollData( dataModel.getIdPollData() );
			answerModel.setIdPollAnswer( answerDAO.generateId() );
			listOp.add( answerDAO.newInsertOpDAO( answerModel ) );
		}
		return answerDAO.updateTransaction( listOp );
	}
	
	public static PollModel loadQuestionary( String name ) throws Exception {
		PollModel pollModel = new PollModel();
		PollDAOFactory daoFactory = PollDAOFactory.getInstance();
		PollIndexDAO indexDAO = daoFactory.getPollIndexDAO();
		PollQuestionDAO questionDAO = daoFactory.getPollQuestionDAO();
		PollOptionDAO optionDAO = daoFactory.getPollOptionDAO();
		// carico il questionario
		PollIndexModel indexModel = indexDAO.loadOneByName( name );
		pollModel.setPollIndexModel( indexModel ); 
		List<PollQuestionModel> listQuestion = questionDAO.loadAllByIndex( indexModel.getIdPollIndex() );
		indexModel.setListPollQuestion( listQuestion );
		Iterator<PollQuestionModel> itQuestion = listQuestion.iterator();
		while ( itQuestion.hasNext() ) {
			PollQuestionModel questionModel = itQuestion.next();
			List<PollOptionModel> listOption = optionDAO.loadAllByQuestion( questionModel.getIdPollQuestion() );
			Iterator<PollOptionModel> itOption = listOption.iterator();
			questionModel.setListPollOption( listOption );
			pollModel.populateOptionMap( questionModel );
			while ( itOption.hasNext() ) {
				PollOptionModel optionModel = itOption.next();
				if ( optionModel.getIdPollOptionParent() != null && optionModel.getIdPollOptionParent().longValue() != 0 && optionModel.getIdPollOptionParent().longValue() != optionModel.getIdPollOption().longValue() ) {
					itOption.remove();
					PollOptionModel optionParentMadel = pollModel.getOptionMap().get( optionModel.getIdPollOptionParent().toString() );
					List<PollOptionModel> kids = optionParentMadel.getListPollOptionKids();
					if ( kids == null ) {
						kids = new ArrayList<PollOptionModel>();
						optionParentMadel.setListPollOptionKids( kids );
					}
					kids.add( optionModel );
				}
			}
		}
		return pollModel;
	}
	
}
