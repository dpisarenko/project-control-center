package at.silverstrike.pcc.api.export2tj3;

import at.silverstrike.pcc.api.conventions.PccException;

/**
 * This exception is thrown, if TaskJugglerExporter is invoked with
 * invalid timingresolution. Timingresolution must equal-to-or-greater-than 15 min
 */
public class InvalidDurationException extends PccException {
    private static final long serialVersionUID = 1L;
    
    private Long taskNumber;
    private String taskName;
    
    public InvalidDurationException(Long taskNumber, String taskName)
    {
        super("Invalid timingresolution. Must be equal-to-or-greater-than 15 min");
        
        this.taskNumber = taskNumber;   
        this.taskName = taskName;
    }
    
    public Long getTaskNumber()
    {
    	return taskNumber;
    }   
    
    public String getTaskName()
    {
    	return taskName;
    }
}
