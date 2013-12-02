package com.scannerapp.apps.framework.view ;

import java.awt.Frame;

import javax.swing.JDialog;

import org.apache.log4j.Logger;

import com.scannerapp.apps.desktop.view.DeskTopPanel;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: System Plus P. Ltd</p>
 * @author Kanhaiya Samariya
 * @version 1.0
 */
public class BaseJDialog
    extends JDialog
{
  private static Logger log = Logger.getLogger(BaseJDialog.class);//For Log4j
  private String classNameWithoutPackage = "" ;
  private BaseController controller ;
  public BaseJDialog ( Frame frame , String title , boolean modal )
  {
    super ( frame , title , modal ) ;
    try
    {
      jbInit () ;
      pack () ;
    }
    catch ( Exception ex )
    {
      ex.printStackTrace () ;
    }
  }

  public BaseJDialog ()
  {
    this ( null , "" , false ) ;
  }

  void jbInit () throws Exception
  {

  }

  public void setController ( BaseController controller )
  {
    this.controller = controller ;
  }

  public BaseController getController ()
  {
    return controller ;
  }

  public void cleanup ()
  {
    removeNotify () ;
  }

  protected void finalize () throws Throwable
  {
    cleanup () ;
    super.finalize () ;
  }

  protected void debug ( String aMsg )
  {
    log.debug(getClassNameWithoutPackage () + " :: " +
                                    aMsg ) ;
  }

  protected void errorLog ( String message , Throwable th )
  {
	  log.debug ( getClassNameWithoutPackage () + " :: " +
                                    message , th ) ;
  }

  protected String getClassNameWithoutPackage ()
  {
    String fullName = getClass ().getName () ;
    int lastIndex = fullName.lastIndexOf ( '.' ) ;
    if ( lastIndex > 0 )
    {
      classNameWithoutPackage = fullName.substring ( lastIndex + 1 ) ;
    }
    else
    {
      classNameWithoutPackage = fullName ;
    }
    return classNameWithoutPackage ;
  }

  public void setStatusText ( String message )
  {
    DeskTopPanel.getInstance ().getStatusPanel ().setStatusText ( message ) ;
  }

  public void clearStatusText ()
  {
    DeskTopPanel.getInstance ().getStatusPanel ().clearStatusText () ;
  }
}
