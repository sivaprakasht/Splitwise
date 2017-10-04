package com;

import com.model.Friend;
import com.model.Spending;
import com.model.Settlement;

import java.util.ArrayList;
import java.util.List;

public class CalculateSplit {


    private List<Friend> createFriends(String friendsInput) {
        List<Friend> friends = new ArrayList<>();
        for (String name : friendsInput.split(",")) {
            friends.add(new Friend(name));
        }
        return friends;
    }

    private List<Spending> createSpendings(String spendingInput, List<Friend> friends) {
        List<Spending> spendings = new ArrayList<>();

        for (Friend friend : friends) {
            Spending spending = new Spending(friend);
            if (spendingInput.contains(friend.getName())) {
                int splitIndex = spendingInput.indexOf(" ", spendingInput.indexOf(" ", spendingInput.indexOf(" ", spendingInput.indexOf(friend.getName())) + 1) + 1);
                if (splitIndex == -1) {
                    splitIndex = spendingInput.length() - 1;
                }
                String friendSpendingInput = spendingInput.substring(spendingInput.indexOf(friend.getName()), splitIndex);
                spending.setAmount(Integer.parseInt(friendSpendingInput.split(" ")[1]));
                spending.setDescription(friendSpendingInput.split(" ")[2]);
            }
            spendings.add(spending);
        }

        return spendings;
    }

    private int getTotalSpend(List<Spending> spendings) {
        int total = 0;
        for (Spending spending : spendings) {
            total = total + spending.getAmount();
        }
        return total;
    }

    private List<Spending> getBalanceUpdatedSpendings(List<Spending> spendings, int splitAmount) {
        List<Spending> updatedSpendings = new ArrayList<>();
        for (Spending spending : spendings) {
            Spending updateSpending = new Spending(spending.getWho(), spending.getAmount(), spending.getDescription());
            updateSpending.setBalance(splitAmount - spending.getAmount());
            updatedSpendings.add(updateSpending);
        }
        return updatedSpendings;
    }

    private List<Settlement> getSplits(List<Spending> spendings) {
        List<Settlement> splitsList = new ArrayList<>();
        for (int i = 0; i < spendings.size(); i++) {
            while (spendings.get(i).getBalance() > 0) {
                for (int j = 0; j < spendings.size(); j++) {
                    if (!spendings.get(j).equals(spendings.get(i)) && spendings.get(j).getBalance() < 0 && spendings.get(i).getBalance() > 0) {
                        if (spendings.get(i).getBalance() > spendings.get(j).getBalance()) {
                            int settledAmount = 0;
                            int balanceAmount = 0;
                            if(spendings.get(i).getBalance() > Math.abs(spendings.get(j).getBalance())) {
                                settledAmount = Math.abs(spendings.get(j).getBalance());
                                balanceAmount = spendings.get(i).getBalance() + spendings.get(j).getBalance();
                                spendings.get(j).setBalance(0);
                            } else {
                                settledAmount = spendings.get(i).getBalance();
                                spendings.get(j).setBalance(spendings.get(j).getBalance() + settledAmount);
                            }

                            spendings.get(i).setBalance(balanceAmount);
                            Settlement splits = new Settlement(spendings.get(i).getWho(), spendings.get(j).getWho(), settledAmount);
                            splitsList.add(splits);
                        }
                    }
                }
            }
        }
        return splitsList;
    }


    public static void main(String a[]) {
        CalculateSplit calculateSplit = new CalculateSplit();
        // Create List of friends from given input string.
        List<Friend> friends = calculateSplit.createFriends("A,B,C,D,E");

        // Create List of spending from the input string.
        List<Spending> spendings = calculateSplit.createSpendings("A 300 Snack C 200 Tickets D 300 Taxi E 100 Others", friends);

        // Identify the equal split amount.
        int splitAmount = calculateSplit.getTotalSpend(spendings) / friends.size();

        // Identify the balance amount of each friends to accept and give.
        spendings = calculateSplit.getBalanceUpdatedSpendings(spendings, splitAmount);

        // Find list of settlement options.
        List<Settlement> settlements = calculateSplit.getSplits(spendings);

        // Print settlement report.
        for (Settlement settlement: settlements) {
            System.out.println(settlement.getSettlementReport());
        }
    }

}
