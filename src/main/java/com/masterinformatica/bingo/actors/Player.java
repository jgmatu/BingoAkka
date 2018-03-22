package com.masterinformatica.bingo.actors;

import com.masterinformatica.bingo.entities.Carton;
import com.masterinformatica.bingo.messages.BingoNumber;
import com.masterinformatica.bingo.messages.BingoExit;
import com.masterinformatica.bingo.messages.BingoMessage;
import com.masterinformatica.bingo.messages.BingoMessage.Value;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Player extends UntypedActor {
	
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private Carton carton;

	public Player() {
    	this.carton = new Carton();
	}
	
    @Override
    public void onReceive(Object message)  throws Exception{    	
    	if (message instanceof BingoNumber) {
    		BingoNumber numb = (BingoNumber) message;
<<<<<<< HEAD
    	
    		this.carton.mark(numb);

    		if (this.carton.isBingo()) {
    			BingoMessage msgLinea = new BingoMessage(Value.BINGO);
        		getSender().tell(msgLinea, getSelf());    			    			
    		} else if (this.carton.isLinea()) {
    			BingoMessage msgLinea = new BingoMessage(Value.LINEA);
        		getSender().tell(msgLinea, getSelf());    			
    		}
    		System.err.println(this.carton.toString());
=======
    		markNumber(numb);
    	} else if (message instanceof BingoExit) {
    		exitGame();
>>>>>>> 841999c3a7aad77c1cdf30de25b57932c6623809
    	} else {
            unhandled(message);
    	}
    }

    private void markNumber(BingoNumber numb) {
		this.carton.mark(numb);

		if (this.carton.isBingo()) {
			BingoMessage msgLinea = new BingoMessage(Value.BINGO);
    		getSender().tell(msgLinea, getSelf());    			    			
    		System.err.println("Bingo!!");
    		System.out.println(this.carton.toString());    		
		} else if (this.carton.isLinea()) {
			BingoMessage msgLinea = new BingoMessage(Value.LINEA);
    		getSender().tell(msgLinea, getSelf());    			
    		System.err.println("Linea!!");
    		System.out.println(this.carton.toString());    		
		}    	
    }
    
    private void exitGame() {
    	if (this.carton.isBingo()) {
    		System.out.println("He ganado!");
    	} else {
    		System.out.println("He perdido...");
    	}
    	getContext().stop(getSelf());
    }
    
    
    @Override 
    public void unhandled(Object message) {
    	log.error("Message not registered");
    }
        
}





























/*public class Jugador extends UntypedActor {

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	private Carton carton;
	
    @Override
    public void preStart() {
    	this.carton = new Carton();
    }

    @Override
    public void onReceive(Object o) {
    	;
    }
    
    @Override 
    public void unhandled(Object o) {
    	log.error("Message not registered");
    }
    
}*/


