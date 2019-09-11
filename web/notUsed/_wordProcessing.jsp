<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%!    public boolean foundSpace(String str) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(str);
        boolean found = matcher.find();
        return found;
    }
%>
<%!
    public List<String> searchWordProcess(String RealValue) {
        List<String> WordForSearching = new ArrayList();
        try {
            String RemoveFromBack = RealValue;
            WordForSearching.add(RealValue);
            if (foundSpace(RemoveFromBack)) {
                while (foundSpace(RemoveFromBack)) {
                    RemoveFromBack = RemoveFromBack.substring(0, RemoveFromBack.lastIndexOf(" "));
                    if (!WordForSearching.contains(RemoveFromBack)) {
                        WordForSearching.add(RemoveFromBack);
                    }
                }
            }
            //Remove from last opposite
            String RemoveFromFront = RealValue;
            if (foundSpace(RemoveFromFront)) {
                while (foundSpace(RemoveFromFront)) {
                    RemoveFromFront = RemoveFromFront.substring(RemoveFromFront.indexOf(32) + 1, RemoveFromFront.length());
                    if (!WordForSearching.contains(RemoveFromFront)) {
                        WordForSearching.add(RemoveFromFront);
                    }

                }
            }
            //Split the word by space
            String SplitWordBySapce = RealValue;
            if (foundSpace(SplitWordBySapce)) {
                String[] word = SplitWordBySapce.split(" ");
                for (String obj : word) {
                    if (!WordForSearching.contains(obj)) {
                        WordForSearching.add(obj);
                    }

                }
            }
        } catch (Exception msg) {
            //out.println(msg);
        }
        return WordForSearching;
    }
%>
<%
    //We can also define as 
// String[] NotSearchableString = {"who", "what", "when", "where", "why", "whome", "whose", "which", "how", "is",
//        "my", "you", "your", "mine"};
//    int SizeOfNotSearchableString = NotSearchableString.length;
//Or we can use like this
//    List<String> notSearchableString = new ArrayList();
//    notSearchableString.add("is");
//    notSearchableString.add("am");
//    notSearchableString.add("are");
%>