/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.VendingMachine;




import com.sg.VendingMachine.Controller.VendingMachineController;
import com.sg.VendingMachine.dao.VendingMachineAuditDao;
import com.sg.VendingMachine.dao.VendingMachineAuditDaoFileIpl;
import com.sg.VendingMachine.dao.VendingMachineDao;
import com.sg.VendingMachine.dao.VendingMachineDaoFileImpl;
import com.sg.VendingMachine.ui.UserIO;
import com.sg.VendingMachine.ui.UserIOConsoleImpl;
import com.sg.VendingMachine.ui.VendingMachineView;
import com.sg.VendingMachine.service.VendingMachineServiceLayer;
import com.sg.VendingMachine.service.VendingMachineServiceLayerImpl;



/**
 *
 * @author Blake
 */
public class App {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
    UserIO myIo = new UserIOConsoleImpl();
    VendingMachineView myView = new VendingMachineView(myIo);
    VendingMachineDao myDao = new VendingMachineDaoFileImpl();
//    Change myChange = new Change();
     VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileIpl();
    VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao); //Took my change out of here as last argument

  
    VendingMachineController controller = 
            new VendingMachineController (myView, myService);
    controller.Run();
    
}
}
