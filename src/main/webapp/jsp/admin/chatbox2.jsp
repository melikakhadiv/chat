<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chat box</title>
    <jsp:include page="../css-import.jsp"></jsp:include>
</head>
<body>
<div class="top"></div>
<div class="box">
    <div class="left">
        <div class="topp">
            <h3>CHATBOX</h3>
        </div>
        <div class="search">
            <input class="in" type="text" placeholder="search buddy..">
           <div class="ico">
<!--               put search icon-->
               <img src="" class="icon1" alt="">
           </div>
        </div>
         <ul>
             <li>
                 <div class="friend">
                     <div class="img-name">
                     <img src="" class="ava" alt="">
                     <div>
                     <h3>Negar</h3>
                     <p>that's good news.</p>
                     </div>
                     </div>
                     <div class="time"><p class="p">Today</p></div>
                 </div>
             </li>

             <li>
                 <div class="friend">
                     <div class="img-name">
                         <img src="" class="ava" alt="">
                         <div>
                             <h3>Mohamad hossein</h3>
                             <p><span>Typing...</span></p>
                         </div>
                     </div>
                     <div class="time"><p class="p">Today</p></div>
                 </div>
             </li>
             <li>
                 <div class="friend">
                     <div class="img-name">
                         <img src="" class="ava" alt="">
                         <div>
                             <h3>Hasan</h3>
                             <p>hello</p>
                         </div>
                     </div>
                     <div class="time"><p class="p">Today</p></div>
                 </div>
             </li>
             <li>
                 <div class="friend">
                     <div class="img-name">
                         <img src="" class="ava" alt="">
                         <div>
                             <h3>Iman</h3>
                             <p>hello</p>
                         </div>
                     </div>
                     <div class="time"><p class="p">Today</p></div>
                 </div>
             </li>
             <li>
                 <div class="friend">
                     <div class="img-name">
                         <img src="" class="ava" alt="">
                         <div>
                             <h3>Maede</h3>
                             <p>hello</p>
                         </div>
                     </div>
                     <div class="time"><p class="p">Today</p></div>
                 </div>
             </li>



         </ul>
    </div>
    <div class="right">
        <div class="right-top">
            <div class="img-name">
                <img src="" class="ava" alt="">
                <div>
                    <h3>Negar</h3>
                    <p>online 30 seconds ago..</p>
                </div>
            </div>
<!--            put three dot icon (more menu)-->
           <img src="" class="icon2" alt="">
        </div>

        <div class="mid">

            <div class="sender">
                <p>hi negar. what's up?</p>
            </div>

            <div class="receiver">
                <p>hello mahoor. nothing really. what about you?</p>
            </div>

            <div class="sender">
                <p>me neither!</p>
            </div>

            <div class="receiver">
                <p>how's the project going?</p>
            </div>

            <div class="sender">
                <p> good good!!</p>
            </div>

            <div class="receiver">
                <p>that's good news.</p>
            </div>
        </div>
        <div class="btm">
            <form>
                <div>+</div>
                <input type="text"  class="in2" placeholder="What's on your mind ?">

<!--                put send (paper plane) icon-->
<!--                <div class="ico3"><img src="" class="send-svg"></div>-->
                <div class="ico3">
                    <span class="paper_plane_icon">
                        <ion-icon name="paper-plane-outline"></ion-icon>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<%--<jsp:include page="../../js-import.jsp"></jsp:include>--%>
</body>
</html>