/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sg.aethon.kumulus.manager.tasks;

/**
 *
 * @author theo
 */
public class ValidationNotificationTask extends NotificationTask
{
    public ValidationNotificationTask()
    {
        super(Status.READY_FOR_VALIDATION,
                "Pending validation work",
                "Please login to your kumulus account and get it done!",
                false);
    }
}
