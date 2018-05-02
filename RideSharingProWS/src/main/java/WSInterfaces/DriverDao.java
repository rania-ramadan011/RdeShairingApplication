/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSInterfaces;

import com.mycompany.ridesharingprows.DriverCarInfo;
import com.mycompany.ridesharingprows.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Rania
 */
public interface DriverDao  extends CrudRepository<DriverCarInfo,Integer> {
      DriverCarInfo findByDriveCarID(int id);
      
    
}
