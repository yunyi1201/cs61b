package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.rmi.server.ServerNotActiveException;
import java.util.HashSet;
import java.util.Set;


public class Solver {

    private Stack<WorldState> ans;

    private class SearchNode implements Comparable<SearchNode> {

        private int distanceFromInitNode;
        private int distanceFromGoal;
        private WorldState Node;
        private SearchNode parent;


        public SearchNode(int d, WorldState w, SearchNode p) {
            distanceFromInitNode = d;
            distanceFromGoal = w.estimatedDistanceToGoal();
            Node = w;
            parent = p;
        }


        @Override
        public int compareTo(SearchNode other) {
           return (this.distanceFromGoal + this.distanceFromInitNode) -
                   (other.distanceFromGoal + other.distanceFromInitNode);
        }

        public int getDistanceFromInitNode() {
            return distanceFromInitNode;
        }

        public boolean isGoalNode() {
            return distanceFromGoal == 0;
        }

        public WorldState getNode() {
            return Node;
        }

        public SearchNode getParent() {
            return parent;
        }

    }

    public Solver(WorldState initial) {
        ans = new Stack<>();

        MinPQ<SearchNode> solutions = new MinPQ<>();
        solutions.insert(new SearchNode(0, initial, null));
        SearchNode goal = null;
        while(!solutions.isEmpty()) {
            SearchNode X = solutions.delMin();
            if (X.isGoalNode())  {
                goal = X;
                break;
            }
            for (WorldState w : X.getNode().neighbors()) {
                if (X.getParent() == null || !w.equals(X.getParent().getNode())) {
                    solutions.insert(new SearchNode(X.getDistanceFromInitNode() + 1, w, X));
                }
            }
        }

        while (goal != null) {
            ans.push(goal.getNode());
            goal = goal.getParent();
        }
    }

    public int moves() {
        return ans.size() - 1;
    }

    public Iterable<WorldState> solution() {
        return ans;
    }

}

