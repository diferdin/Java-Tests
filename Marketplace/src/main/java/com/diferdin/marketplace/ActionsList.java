package com.diferdin.marketplace;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by LONADF on 08/06/2016.
 */

public class ActionsList<T extends Action> {
    private List<T> actions;

    public ActionsList() {
        actions = new ArrayList<T>();
    }

    public boolean add(T action) {
        if(actions == null) {
            actions = new ArrayList<>();
        }

        if(actions.contains(action)) {

            increaseQuantity(action);

            return true;
        } else {

            return  actions.add(action);
        }
    }

    private boolean increaseQuantity(Action action) {
        for(Action actionInList : actions) {
            if(actionInList.getItemId().equals(action.getItemId()) &&
                    actionInList.getUser().equals(action.getUser()) &&
                    actionInList.getPricePerUnit() == action.getPricePerUnit() &&
                    actionInList.getType().equals(action.getType())) {

                actionInList.setQuantity(actionInList.quantity + action.getQuantity());
                actionInList.setTimestamp(action.getTimestamp());

                return true;
            }
        }
        return false;
    }

    public int size() {
        return actions.size();
    }

    public Optional<T> get(String actionId) {
        for(T action : actions) {
            if(actionId.equals(action.getId())) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }

    public T get(int index) {
        return actions.get(index);
    }

    public boolean remove(T actionToRemove) {
        for(T action : actions) {
            if(actionToRemove.equals(action)) {
                return actions.remove(action);
            }
        }

        return false;
    }

    public List<T> retrieveActionsByUser(String userId) {
        return actions.stream()
                .filter(a -> userId.equals(a.getUser()))
                .collect(Collectors.toList());
    }

    public List<T> retrieveActionsByItemId(String itemId) {
        return actions.stream()
                .filter(a -> itemId.equals(a.getItemId()))
                .collect(Collectors.toList());
    }

    public boolean contains(Action action) {
        if(actions == null) {
            return false;
        }

        if(actions.isEmpty()) {
            return false;
        }

        for(Action actionInList : actions) {
            if(action.getItemId().equals(actionInList.getItemId()) &&
                    action.getUser().equals(actionInList.getUser()) &&
                    action.getType().equals(actionInList.getType()) &&
                    action.getPricePerUnit() == actionInList.getPricePerUnit()) {
                return true;
            }
        }

        return false;
    }

    public boolean decreaseQuantity(T action, int decrease) {
        if(actions == null) {
            return false;
        }

        if(actions.size() == 0) {
            return false;
        }

        T matchingAction = actions.stream().filter(a -> a.getId().equals(action.getId())).collect(Collectors.toList()).get(0);

        int matchingActionQuantity = matchingAction.getQuantity();

        if((matchingActionQuantity - decrease) == 0) {
            actions.remove(action);
        } else {
            actions.stream().filter(a -> a.getId().equals(action.getId()))
                    .forEach(a -> a.setQuantity(a.getQuantity() - decrease));
        }

        return true;
    }

    public List<T> getAll() {
        return actions;
    }
}
