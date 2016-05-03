<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-default">
    <div class="panel-heading">    
        <h3>Articles in the ${category.categoryName} category</h3>
    </div>
    <div class="panel-body">

        <div id="staticPageContentRows">


            <c:forEach var="staticPage" items="${staticPages}">

                <a href="/CMS/pagelink/${staticPage.titleNumber}">${staticPage.title}</a>
                <br/>
                
            </c:forEach>



        </div>
    </div>
</div>