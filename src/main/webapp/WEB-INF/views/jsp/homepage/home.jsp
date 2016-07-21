<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/pageContext.jsp"%>
<html lang="en">

<head>
<meta charset="utf-8" />

<title>Wedding</title>

<script src="${path}/js/jquery.js"></script><!--/must be first-->

<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=PT+Serif' rel='stylesheet' type='text/css'>

<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->  

<!--/for 960grid-->
<link rel="stylesheet" media="all" href="${path}/css/reset.css" />
<link rel="stylesheet" media="all" href="${path}/css/text.css" />
<link rel="stylesheet" media="all" href="${path}/css/960.css" />
  
  
<link rel="stylesheet" href="${path}/css/main.css" media="screen"/>


<link rel="stylesheet" href="${path}/css/pictureslider.css" media="screen" /><!--css for picture slider-->
   
<link rel="stylesheet" href="${path}/css/nivo-slider.css" media="screen" /><!--css for drop down-->


<!--[if IE]> 
<link rel="stylesheet" href="${path}/css/ie.css" />
<![endif]--><!--/all ie browsers-->


     
<script src="${path}/js/script.js"></script>


<!--/image fade-->
<script>
$(document).ready(function() {
    $('.pic').delay(1000).fadeIn(1500);
 $('.pic1').delay(1000).fadeIn(1500);
$('.pic2').delay(1000).fadeIn(1500);
 $('.pic3').delay(1000).fadeIn(1500);
$('.pic4').delay(1000).fadeIn(1500);
    $('.picslide').delay(1000).fadeIn(1500);
 $('.portraitpic').delay(1000).fadeIn(1500);

});
</script>
<!--/image fade-->


<!--/picture hover-->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<link href="styles.html" rel="stylesheet"  />
<script src="${path}/js/hover.js"></script>

<!--/picture hover-->

<!--/IE curvy corners-->
<script type="text/javascript" src="${path}/js/curvy.corners.trunk.js"></script>
<!--/IE curvy corners-->

</head>

<body>


<div class="container_12"> 
 
 <header class="grid_12 alpha">  
 
  
      <nav class="menuline"> 
  
<div class="grid_2"> 
<h1 class="logo">Him and Her</h1>

 </div><!--/grid_2-->     
  

<ul class="menu" id="menu">



<li>
		<a href="home.jsp" class="menulink firstradius">Home</a>
              		<ul>
                        
                        <li><a href="home.jsp" class="firstrightradius">Home 1</a></li>
                  <li><a href="home_2.html" class="lastradius">Home 2</a></li>
                        

		</ul>      

</li>
<li>
		<a href="wedding.html" class="radius menulink">Wedding</a>
 
        
	
</li>


<li>
		<a href="about.html" class="menulink radius">About</a>

</li>

<li>
		<a href="photos_2.html" class="menulink firstradius">Photos</a>
		<ul> 

                      <li><a href="photos_1.html" class="firstrightradius">Photos 3 columns</a></li>
                        <li><a href="photos_2.html">Photos 4 columns</a></li>
                      <li><a href="photos_3.html" class="lastradius">Circle photos</a></li>

		</ul>
</li>



<li>
		<a href="blog_index.html" class="menulink firstradius">Blog</a>
      <ul>
                       <li><a href="blog_index.html" class="firstrightradius">Blog index</a></li>
                        <li><a href="blog_post.html" class="lastradius">Blog post</a></li>
			   
		</ul>
</li>




<li><a href="rsvpcontact.html" class="menulink radius">RSVP/Contact</a></li>


</ul>

</nav>    <!--/menuline-->  
    
   

 </header>
       <!--/grid_12-->  


   

<section class="sliderbg grid_12"> 

   
<section class="slider grid_8"> 
      
<div class="slider-wrapper theme-default">

                <div id="slider" class="nivoSlider picslide">
               
                <img src="${path}/images/1.jpg" alt="picture" />
                  <img src="${path}/images/2.jpg" alt="picture" />
  
                  <img src="${path}/images/3.jpg" alt="picture" />
               </div>
         
      </div>

    <script src="${path}/js/jquery.nivo.slider.pack.js"></script>
  <script>
$(window).load(function() {
    $('#slider').nivoSlider({
        effect: 'fade', // Specify sets like: 'fold,fade,sliceDown'
        animSpeed: 500, // Slide transition speed
        pauseTime: 3000, // How long each slide will show
        startSlide: 0, // Set starting Slide (0 index)
        directionNav: false, // Next & Prev navigation
        directionNavHide: true, // Only show on hover
        controlNav: true, // 1,2,3... navigation
        keyboardNav: false, // Use left & right arrows
        pauseOnHover: true, // Stop animation while hovering
       
    });
});
</script>

        
  </section><!--/grid_8-->



<header class="grid_4 alpha omega"> 


<h1 class="textslider">Wedding Day</h1> 

<h1 class="textslider1">15<small>th</small> July 2012.</h1> 
<h1 class="textslider2">08:00am - 03:00 pm</h1> 
<h1 class="textslider3">Wedding Place</h1> 


  </header><!--/grid4-->   

 </section><!--/sliderbg-->  



<section class="portraits grid_12"> 


<section class="portraitsleft grid_6 alpha"> 


<section class="portraitbgleft"> 


<img src="${path}/images/portraitleft.png" class="portraitpic" alt="picture" />

</section> 




<section class="portraitsl grid_3 alpha omega"> 


<h2 class="portraitheadlineleft">About Her</h2>
<p class="portraittextleft">Augue ut erat a vel nulla. Iure quisque commodo. A sit ipsum. Ut con mollis. Vel non in mauris.</p>

<a href="#" class="buttongreyleft" title="button"><span>read more</span></a>


 </section><!--/portraitsgrid_3-->  


 </section><!--/portraitsgrid_6-->  




<section class="portraitsright grid_6 omega"> 



<section class="portraitbgright"> 
<img src="${path}/images/portraitright.png" class="portraitpic" alt="picture" />

</section> 


<section class="portraitsr grid_3 omega "> 


<h2 class="portraitheadlineright">About Him</h2>
<p class="portraittextright">Augue ut erat a vel nulla. Iure quisque commodo. A sit ipsum. Ut con mollis. Vel non in mauris.</p>




<a href="#" class="buttongreyright" title="button"><span>read more</span></a>


 </section><!--/portraitsgrid_3-->  


 </section><!--/portraitsgrid_6-->  



 </section><!--/portraitsgrid_12-->  





<section class="columns grid_12"> 


<section class="circlelinetop grid_12"> 
</section><!--/circleline-->  


<section class="columninside grid_12"> 



<section class="grid_6 omega"> 

<section class="grid_3 alpha"> 

<h2 class="columnheadline">Our Wedding</h2>


<div class="pic1"> 

</div> 


 <p class="columntext">Pellentesque justo quis erat lectus ed. Eius nibh nunc tellus at nibh.  </p>

<a href="#" class="buttoncolor" title="button"><span>read more</span></a>


 </section><!--/column-->  




<section class="grid_3 alpha"> 

<h2 class="columnheadline">Our Honeymoon</h2>


<div class="pic2">

</div><!--/pic-->  

 <p class="columntext">Pellentesque justo quis erat lectus ed. Eius nibh nunc tellus at nibh.  </p>

<a href="#" class="buttoncolor" title="button"><span>read more</span></a>


 </section><!--/column-->  


 </section><!--/grid6-->  


<section class="grid_6 alpha omega"> 

<section class="grid_3 alpha"> 

<h2 class="columnheadline">Gift Registry</h2>


<div class="pic3">

</div><!--/pic-->  

 <p class="columntext">Pellentesque justo quis erat lectus ed. Eius nibh nunc tellus at nibh.  </p>

<a href="#" class="buttoncolor" title="button"><span>read more</span></a>


 </section><!--/column-->  
 



<section class="grid_3 alpha"> 

<h2 class="columnheadline">RSVP/Contact</h2>


<div class="pic4">

</div><!--/pic-->  

 <p class="columntext">Pellentesque justo quis erat lectus ed. Eius nibh nunc tellus at nibh.  </p>

<a href="#" class="buttoncolor" title="button"><span>read more</span></a>


 </section><!--/column-->  

 </section><!--/grid6-->  



 </section><!--/columninside-->  

<section class="circlelinebottom grid_12"> 
</section><!--/circleline-->  


 </section><!--/columns-->  




<section class="footerwrapper grid_12"> 


<div class="footer"> 

<section class="grid_4 alpha"> 

<h3 class="footerheadline1">From the Blog</h3>


<p class="footertext1"><b>Blog post</b></p>

<p class="footertext1">Lorem ipsum dolor sit amet, consectetur adi tellus.</p>
<p class="footertext1a"><i>15th May by Someone</i></p>
<a href="#" class="readmorefooter">read more</a>


 </section><!--/grid_4-->
 
 <section class="grid_4 alpha omega"> 

<h3 class="footerheadline2">Latest Twitts</h3>

<p class="footertext2"><a href="#" class="twfooter">@Someone:</a>Lorem ipsum dolor sit amet, adi tellus pisi cing elit sed to..</p>
<p class="footertext2a"><i>about 1 hour ago</i></p>

 </section><!--/grid_4-->
 
 <section class="grid_4 omega"> 


<h3 class="footerheadline3">Get Social</h3>


 <section class="iconsfooter"> 
<a href="#" class="iconfooter"><img src="${path}/images/iconfb.png" alt="Facebook" /></a>

<a href="#" class="iconfooter"><img src="${path}/images/icongoogleplus.png" alt="Googleplus" /></a>

<a href="#" class="iconfooter"><img src="${path}/images/icontwitter.png" alt="Twitter" /></a>

<a href="#" class="iconfooter"><img src="${path}/images/iconcontact.png" alt="Contact" /></a>
 </section><!--/iconsfooter-->



 </section><!--/grid_4-->


 </div><!--/footer-->



 </section><!--/footerwrapper-->

 <footer class="footerbottom grid_12">  

<p class="copyright">Copyright 2013. All rights reserver.</p>

 <section class="bottomlinks">  
<ul class="bottomlinks">
			<li><a href="#" class="links">contact</a></li>
     	<li><a href="#" class="links">blog</a></li>
       
        	
			<li><a href="#" class="links">photos</a></li>
			<li><a href="#" class="links">home</a></li>

		</ul>
</section> 
</footer> 


 </div><!--/container_12-->


<!--/dropdown menu script-->

<script>
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");

</script>

<!--/dropdown menu script-->

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
