/*
 * Copyright 2019 AngryLion.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.modalset;

import com.string.validateInput;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AngryLion
 */
@WebServlet(name = "modal", urlPatterns = {"/modal"})
public class modal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        validateInput input = new validateInput();
        ModalClass mc = new ModalClass();

        List<QuestionSetPojo> initilizeArrayList = null;
        HashMap<Integer, String> setQuestionById = null;

        String gotException = null;
        String exam_of = null;
        int set_no = 1;

        try {

           /* Object attribute = request.getAttribute("initilizeArrayListFromUser");

            System.out.println("Attrinbute ->" + attribute);

            String parameter = request.getParameter("initilizeArrayListFromUser");
            String[] g = parameter.split(",");

            try {
                for (String string : g) {
                    System.out.println("\nModified the obj -> " + string);
                    
                }
            } catch (Exception d) {
                System.out.println("Excaption in " + d);
            }

            System.out.println("Parameter -> " + parameter);
            Object object = parameter;
            System.out.println("object -> " + object);

            try {
                String[] parameterValues = request.getParameterValues("v[]");
                for (String parameterValue : parameterValues) {
                    System.out.println("Ans Param value -> " + parameterValue);
                }
            } catch (Exception d) {
                System.out.println("Excaption in " + d);
            }

            try {
                String[] ii = request.getParameterValues("q[]");
                for (String iii : ii) {
                    System.out.println(" q id Param value -> " + iii);
                }
            } catch (Exception d) {
                System.out.println(" q id Excaption in " + d);
            }*/

            exam_of = "computer";//input.getInputString(request.getParameter("exam_of"));
            set_no = 1;// input.getInputInt("1");
            int questionId = input.getInputInt(request.getParameter("2"));
            String selected_answer = input.getInputString(request.getParameter("selectedAns"));

            int arraySize = mc.arraySise(exam_of, set_no);

            //inilitize the array size
            int[] arr = new int[arraySize];

            //initlize the arrayList
            initilizeArrayList = mc.initilizeArrayList(exam_of, set_no);

            //fun(question_id, correct_answer, user_selected_anser)
          /*  int i = 0;
            for (QuestionSetPojo obj : initilizeArrayList) {
                int questionId1 = obj.questionId;
                //System.out.println("\n storing in array" + questionId1);
                arr[i] = questionId1;
                i++;
            }

            for (int o : arr) {
                System.out.println("Question Id -> " + o);
            }*/

            setQuestionById = mc.getSetQuestionById(questionId + 1);

        } catch (ClassNotFoundException | SQLException msg) {
            gotException = "not null";
            Logger.getLogger(modal.class.getName()).log(Level.SEVERE, null, msg);
        } finally {
            request.setAttribute("gotException", gotException);
            request.setAttribute("exam_of", exam_of);
            request.setAttribute("set_no", set_no);

            request.setAttribute("initilizeArrayList", initilizeArrayList);
            request.setAttribute("setQuestionById", setQuestionById);

            request.getRequestDispatcher("modal.jsp").forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
