package com.diferdin.marketplace;

import com.diferdin.marketplace.exception.ActionException;

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

            if(actionInList.equals(action)) {
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

        List<T> filteredActions = actions.stream()
                .filter(a -> a.getId().equals(actionId))
                .collect(Collectors.toList());

        if(filteredActions.size() == 0) {
            return Optional.empty();
        }

        if(filteredActions.size() > 1) {
            throw new ActionException("Action with ID "+actionId+" seems to have multiple occurrences.");
        }

        return Optional.of(filteredActions.get(0));
    }

    public T get(int index) {

        if(index < 0) {
            throw new ActionException("Index cannot be negative.");
        }

        if(index >= actions.size()) {
            throw new ActionException("Index exceeding actions list size.");
        }

        return actions.get(index);
    }

    public boolean remove(T actionToRemove) {

        List<Action> filteredActions = actions.stream()
                .filter(a -> a.equals(actionToRemove))
                .collect(Collectors.toList());

        if(filteredActions.size() != 1) {
            return false;
        } else {
            return actions.remove(actionToRemove);
        }
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
        if(actions == null || actions.isEmpty()) {
            return false;
        }

        List<Action> retrievedActions = actions.stream().filter(a -> a.equals(action))
                .collect(Collectors.toList());

        return retrievedActions.size() > 0;
    }

    public boolean decreaseQuantity(T action, int decrease) {
        if(actions == null || actions.size() == 0) {
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

    public int getMaxPriceForItemId(String itemId) {
        Optional<T> action = actions.stream()
                .filter(a -> a.getItemId().equals(itemId))
                .max((o1, o2) -> o1.getPricePerUnit() - o2.getPricePerUnit());

        if(!action.isPresent()) {
            return 0;
        }

        return action.get().getPricePerUnit();
    }
}
