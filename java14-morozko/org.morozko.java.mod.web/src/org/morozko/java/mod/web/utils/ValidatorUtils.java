package org.morozko.java.mod.web.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.morozko.java.mod.daogen.helpers.bean.BasicBean;
import org.morozko.java.mod.daogen.helpers.model.BasicModel;

public class ValidatorUtils {
	
    public static final BasicModel FORMAT = new BasicModel();
    
    public static final BasicBean DEFORMAT = new BasicBean();

    public static Integer parseAnno( Date d ) {
        Calendar c = Calendar.getInstance();
        c.setTime( d );
        return new Integer( c.get( Calendar.YEAR ) );
    }
    
    public static String defValue( String value, String def ) {
        String res = value;
        if ( res == null ) {
            res = def;
        }
        return res;
    }
    
	public static boolean isNull( String s ) {
		return s==null || s.equals("");
	}
	
	public static void addCustomError( ActionErrors errors, String message ) {
	    Object[] args = { message };
	    errors.add( "errors", new ActionMessage( "struts.message.validate.generic.errors.custom", args ) );
	}
	
	public static void addMessage( String key, ActionErrors errors ) {
	    errors.add( "errors", new ActionMessage( key ) );
	}

	public static void addError( ActionErrors errors, String key ) {
	    errors.add( "errors", new ActionMessage( key ) );
	}
	
	public static void main( String[] args ) {
		String testDate = "28/02/2008";
		ActionErrors errors = new ActionErrors();
		System.out.println( fieldDate( "Test Date" , testDate, errors, false, null, null ) );
	}
	
	/**
	 * Verifica la validit&agrave; di una data :
	 * - validit&agrave; formale della strigna data (GG/MM/AAAA)
	 * - verifica che la data sia superiore o uguale di min (non controllato se <code>min == null</code>)
	 * - verifica che la data sia inferiore o uguale di max (non controllato se <code>max == null</code>)
	 * 
	 * @param field			il nome del campo
	 * @param value			il valore del campo da validare
	 * @param errors		la lista dove eventualmente salvare gli errori
	 * @param optional		se vale <code>true</code> tutti gli controlli vengono ignorati qualira <code>value==null || value.equals("")</code>
	 * @param min			data minima di value ( non viene verificato se <code>min == null</code>)
	 * @param max			data massima di value ( non viene verificato se <code>max == null</code>)
	 * @return				<code>true</code> se tutte le validazioni vengono superate (e quindi non sono stati aggiunti errori ad errors).
	 */
	
	public static boolean fieldDate( String field, String value, ActionErrors errors, boolean optional, String min, String max ) {
		return fieldDate( field, value, errors, optional, min, max, BasicModel.newDateFormat() );
	}
	
	public static boolean fieldDate( String field, String value, ActionErrors errors, boolean optional, String min, String max, DateFormat dateFormat ) {
		boolean result = true;
		if ( !( optional && isNull( value ) ) ) {
			try {
			    Date valueDate = dateFormat.parse( value );
			    if ( min != null ) {
			        Date dateMin = dateFormat.parse( min );
			        if ( valueDate.before( dateMin ) ) {
			            Object[] args = { field, min };
					    errors.add( "errors", new ActionMessage( "struts.message.validate.field.date.minDate", args ) );
					    result = false;
			        }
			    }
			    if ( max != null ) {
			        Date dateMax = dateFormat.parse( max );
			        if ( valueDate.after( dateMax ) ) {
			            Object[] args = { field, max };
					    errors.add( "errors", new ActionMessage( "struts.message.validate.field.date.maxDate", args ) );
					    result = false;
			        }
			    }
			} catch (Exception e) {
			    result = false;
			    Object[] args = { field };
			    errors.add( "errors", new ActionMessage( "struts.message.validate.field.date.error", args ) );
			}
		}
		return result;
	}	
	
	public static boolean fieldDouble( String field, String value, ActionErrors errors, boolean optional, long min, long max ) {
		return fieldDouble( field, value, errors, optional, min, max, -1 );
	}
	
	public static boolean fieldDouble( String field, String value, ActionErrors errors, boolean optional, long min, long max, int maxDigit ) {
		boolean result = optional || fieldRequired( field, value, errors );
		String originalValue = value;
		if ( result && !isNull( value ) ) {
			try {
			    System.err.println( "TEST fieldDouble 1 : "+value );
			    value = value.replaceAll( "\\.", "" );
			    System.err.println( "TEST fieldDouble 2 : "+value );
			    value = value.replaceAll( ",", "." );
			    System.err.println( "TEST fieldDouble 3 : "+value );
			    double valueLong = Double.parseDouble( value );
			    System.err.println( "TEST fieldDouble primo if : "+value );
			    if ( valueLong < min ) {
			    	System.err.println( "TEST fieldDouble primo if : "+value );
			        Object[] args = { field, new Long( min ) };
				    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberMin", args ) );    
			    }
			    if ( valueLong > max ) {
			        Object[] args = { field, new Long( max ) };
				    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberMax", args ) );    
			    }
			} catch (Exception e) {
			    result = false;
			    Object[] args = { field };
			    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberError", args ) );
			}
		}
		if ( maxDigit != -1 && result ) {
			result = fieldMaxDecimalDigit( field, originalValue, errors, optional, maxDigit );
		}
		return result;
	}	
	
	/**
	 * Verifica la validit&agrave; di una data :
	 * - validit&agrave; formale della strigna rappresentante il numero,in particolare i punti(.), non vengono interpretati come virgole, ma vengono ignorati (vedi NOTA )
	 * - verifica che il numero sia superiore o uguale di min (eventualmente imporre <code>Long.MIN_VALUE</code> per il minimo valore possibile)
	 * - verifica che il numero sia inferiore o uguale di max (eventualmente imporre <code>Long.MAX_VALUE</code> per il massimo valore possibile)
	 *  
	 * NOTA: i punti vengono ignorati, in quanto fanno parte della formattazione standard delle migliaia ( es 1.000).
	 * 			pertanto, un controllo sugli stessi implicherebbe che l'utente dovrebbe sempre riposizionarli correttamente
	 * 			all' atto dell' inserimento del campo. 
	 * 
	 * @param field			il nome del campo
	 * @param value			il valore del campo da validare
	 * @param errors		la lista dove eventualmente salvare gli errori
	 * @param optional		se vale <code>true</code> tutti i controlli vengono ignorati qualora <code>value==null || value.equals("")</code>
	 * @param min			valore minimo che pu&ograve; assumere il campo
	 * @param max			valore massimo che pu&ograve; assumere il campo
	 * @return
	 */
	public static boolean fieldLong( String field, String value, ActionErrors errors, boolean optional, long min, long max ) {
		boolean result = optional || fieldRequired( field, value, errors );
		if ( result && !isNull( value ) ) {
			try {
			    value = value.replaceAll( "\\.", "" );
			    long valueLong = Long.parseLong( value );
			    if ( valueLong < min ) {
			        Object[] args = { field, new Long( min ) };
				    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberMin", args ) );
				    result = false;
			    }
			    if ( valueLong > max ) {
			        Object[] args = { field, new Long( max ) };
				    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberMax", args ) );
				    result = false;
			    }
			} catch (Exception e) {
			    result = false;
			    Object[] args = { field };
			    errors.add( "errors", new ActionMessage( "struts.message.validate.double.numberError", args ) );
			}
		}
		return result;
	}
	
	public static boolean fieldString( String field, String value, ActionErrors errors, boolean optional, int min, int max ) {
		boolean result = optional || fieldRequired( field, value, errors );
		if ( result && !isNull( value ) ) {
			if ( value.length()<min ) {
				result = false;
				Object[] args = { field, new Integer( min ) };
				errors.add( "errors", new ActionMessage( "struts.message.validate.field.string.minLengh", args ) );						
			} else if ( value.length()>max ) {
				result = false;
				Object[] args = { field, new Integer( max ) };
				errors.add( "errors", new ActionMessage( "struts.message.validate.field.string.maxLengh", args ) );
			}
		}
		return result;
	}
    
	public static boolean fieldRequired( String field, Object value, ActionErrors errors ) {
		boolean result = true;
		if (value==null || value.equals("") || value.equals(" ") ) {
			Object[] args = { field };
			errors.add( "errors", new ActionMessage( "struts.message.validate.field.required", args ) );
			result = false;
		}
		return result;
	}	

	public static boolean fieldRequired( String field, String value, ActionErrors errors ) {
		boolean result = true;
		System.out.println("fieldRequired: "+value);
		if (value==null || value.equals("") || value.equals(" ")) {
			System.out.println("fieldRequired nell'if");
			Object[] args = { field };
			errors.add( "errors", new ActionMessage( "struts.message.validate.field.required", args ) );
			result = false;
		}
		return result;
	}
	public static boolean fieldMaxDecimalDigit( String field, String value, ActionErrors errors, boolean optional, int max ) {
		//errore Se dopo la virgola ci sono piu' di max cifre
		boolean result = optional || fieldRequired( field, value, errors );
		System.out.println("RESULT: "+result);
		if ( result && !isNull( value ) ) {
			if(value.indexOf(',')>0){
       		 if(value.length()-value.indexOf(',')>max+1){
       		 		Object[] args = { field, new Integer( max)};
       		 		errors.add( "errors", new ActionMessage( "struts.messages.secin.tng.generic.errors.field.required.tooManyDigits", args ) );
       		 		result = false;
       		 		//BeanFacade.addCustomError( errors, "il campo percentuale deve contenere al massimo due decimali" );	
       	}	
			
		}
		}	
		return result;
	}
	
}
