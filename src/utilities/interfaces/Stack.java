package utilities.interfaces;
// Programmiersprachen 1 (Java)
// Prof. Dr. H. G. Folz
// 2001
//
 

/** Abstrakte Klasse Stack */
public abstract class Stack {
   /** Ist der Stack leer?     */
   public boolean empty () {
       return (size() == 0);
   }
   /** Ist der Stack voll?      */
   public abstract boolean full  ();

   /** Ein Element in den Stack einfuegen
       Vorbedingung: !full()            */
   public abstract void push (Object o) ;

   /** Oberstes Element Entfernen
       Vorbedingung: !empty()           */
   public abstract void pop ();

   /** Wert des obersten Elementes zurueckgeben
       Vorbedingung: !empty()           */
   public abstract Object top ();

   /** Anzahl Elemente des Stack        */
   public abstract int size();
}
