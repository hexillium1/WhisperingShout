package me.hexillium.whisperingshout.commands;

/**
 * Used mostly for setting the type of chat that the player has toggled.
 */
public enum ChatType {

    UNSPECIFIED(0),
    OUT_OF_CHARACTER(1),
    SHOUT(2),
    TALK(3),
    WHISPER(4),
    ;

    int type;

    private ChatType(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public static ChatType fromInt(int type){
        switch(type){
            case 0:
                return UNSPECIFIED;
            case 1:
                return OUT_OF_CHARACTER;
            case 2:
                return SHOUT;
            case 3:
                return TALK;
            case 4:
                return WHISPER;
        }
        return UNSPECIFIED;
    }

}
