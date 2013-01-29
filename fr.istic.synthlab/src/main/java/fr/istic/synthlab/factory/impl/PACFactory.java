package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.IPFactory;




public class PACFactory {
   

   protected static IFactory aFactory ;
   

   public static void setAFactory (IFactory f) {
      aFactory = f ;
   }
   
   public static IFactory getAFactory () {
      return (aFactory) ;
   }

   protected static IFactory cFactory ;

   public static void setCFactory (IFactory f) {
      cFactory = f ;
   }    
   
   public static IFactory getCFactory () {
      return (cFactory) ;
   }
 
   protected static IPFactory pFactory ;
   

   public static void setPFactory (IPFactory f) {
      pFactory = f ;
   }
  
   public static IPFactory getPFactory () {
      return (pFactory) ;
   }
   
   public static void setFactory (IFactory f) {
      aFactory = f ;
   }

   public static IFactory getFactory () {
      IFactory factory = aFactory ;
      if (cFactory != null) {
         factory = cFactory ;
      }
      return (factory) ;
   }
   
}
