package fr.ilicos.gameTemplate;

/**
 * Created by ilicos, Th�o S. on 08/08/2015.
 */
public interface ConnectionDisconnection {
    void onPlayerConnection(PlayerContainer playerContainer);
    void onPlayerDisconnection(PlayerContainer playerContainer);
}
