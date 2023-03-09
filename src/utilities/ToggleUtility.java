package utilities;

import views.MainForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/*
    Utility class for toggle button functions.
 */
public class ToggleUtility {

    public static void setToggleButtonStates(Map<String, ArrayList<Boolean>> states,
                                             Map<String, ArrayList<JToggleButton>> toggleButtonsGroup)
    {
        for (var i : states.keySet()) {
            for(int l = 0; l < toggleButtonsGroup.get(i).size(); l++)
            {
                toggleButtonsGroup.get(i).get(l).setSelected(states.get(i).get(l));
            }
        }
    }

    public static String getActiveToggle(String groupName,
                                         Map<String, ArrayList<JToggleButton>> toggleButtonsGroup,
                                         Map<String, String[]> pizzaData)
    {
        ArrayList<String> activeStyle = new ArrayList<String>();
        var buttonStates = getToggleButtonStates(toggleButtonsGroup).get(groupName);

        for(int i = 0; i < buttonStates.size(); i++)
            if (buttonStates.get(i))
                activeStyle.add(pizzaData.get(groupName)[i]);

        if (activeStyle.size() == 1)
            return activeStyle.get(0);
        else return String.join(", ", activeStyle);
    }




    public static void setToggleButtonStates(Map<String, ArrayList<JToggleButton>> toggleButtonsGroup,
                                                                         boolean value) {
        for (var i : toggleButtonsGroup.keySet()) {
            for(var state : toggleButtonsGroup.get(i)) {
                    state.setSelected(value);
            }
        }
    }

    public static  Map<String, ArrayList<Boolean>> getToggleButtonStates(Map<String, ArrayList<JToggleButton>> toggleButtonsGroup) {
        Map<String, ArrayList<Boolean>> states = new Hashtable<>();
        for (var i : toggleButtonsGroup.keySet()) {
            ArrayList<Boolean> toggleState = new ArrayList<>();
            for(var state : toggleButtonsGroup.get(i))
            {
                toggleState.add(state.isSelected());
            }
            states.put(i, toggleState);
        }
        return states;
    }


}
