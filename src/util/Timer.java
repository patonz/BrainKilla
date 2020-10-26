package util;

/**
 *  Questa classe permette di ottenere il tempo di esecuzione del sistema 
 *  tramite la rilevazione del tempo all'inizio e alla fine di un insieme di 
 *  istruzioni
 *  @Author Alice Casari
 *  @version 1.0 2009.11.08
 */
 
public final class Timer
{
    private static long initialTime;
    private static long finalTime;
    private static long systemTime;

    /**
     *  Rileva il tempo iniziale
     */
    public static void start()
    {
        initialTime = System.currentTimeMillis();
    }

    /**
     *  Rileva il tempo finale
     */
    public static void end()
    {
        finalTime = System.currentTimeMillis();
    }

   /**
     *  Determina il tempo di esecuzione del sistema in secondi
     *  @return tempo di esecuzione del sistema in millisecondi
     */
    public static long getSysTimeMilliSec()
    {
        systemTime = (finalTime - initialTime);
        return systemTime;
    }

    /**
     *  Determina il tempo di esecuzione del sistema approssimato in secondi
     *  @return tempo di esecuzione del sistema in secondi
     */
    public static long getSysTimeSec()
    {
        systemTime = (long) ((finalTime - initialTime)/1000. + 0.5);
        return systemTime;
    }
}

