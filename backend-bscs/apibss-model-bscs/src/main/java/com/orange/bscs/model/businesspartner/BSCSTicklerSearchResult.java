package com.orange.bscs.model.businesspartner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Result having List and SearchIsComplete.
 * 
 * INFO : This is not a BSCSModel.
 * 
 * @author IT&Labs
 *
 */
public class BSCSTicklerSearchResult implements Serializable {

    private static final long serialVersionUID = -4008639573030713752L;

    private List<BSCSTickler> result;
    private boolean searchIsComplete;
    
    public BSCSTicklerSearchResult(){
        result=new ArrayList<BSCSTickler>();
    }

    public BSCSTicklerSearchResult(List<BSCSTickler> result, boolean isComplete){
        this.result=result;
        this.searchIsComplete=isComplete;
    }
    
    public List<BSCSTickler> getResult() {
        return this.result;
    }
    public void setResult(List<BSCSTickler> result) {
        this.result = result;
    }
    public boolean isSearchIsComplete() {
        return this.searchIsComplete;
    }
    public void setSearchIsComplete(boolean searchIsComplete) {
        this.searchIsComplete = searchIsComplete;
    }
    
}
