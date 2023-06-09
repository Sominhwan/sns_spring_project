<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modal with Scroll</title>
	<style>
		body {
			margin: 0;
			padding: 0;
			background-color: #e6e6e6;
		}
		.modal {
			display: flex;
			align-items: center;
			justify-content: center;
			position: fixed;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			background-color: rgba(0, 0, 0, 0.5);
			z-index: 1;
			overflow: auto;
		}
		.modal-content {
			position: relative;
			background-color: white;
			width: 1920px;
			height: 2080px;
			box-sizing: border-box;
			overflow: scroll;
			overflow-x: scroll;
		}
		.close-button {
			position: absolute;
			top: 10px;
			right: 10px;
			font-size: 30px;
			cursor: pointer;
			color: #aaa;
		}
		 /* 탐색 글자 스타일 */
		.search-text {
			font-size: 24px;
			font-weight: bold;
			margin-top: 20px;
			margin-bottom: 20px;
			text-align: left;
			position: absolute;
			top: 126px;
			left: 383px;
			transform: translateX(-50%);
			z-index: 3; /* 추가 */
		}
		.image-row {
	        display: flex;
	        flex-wrap: wrap;
	        margin-top: 40px;
	        gap: 10px; /* 간격 조절 */
   		}

    	.image-container {
	        width: 320px;
	        height: 320px;
	        position: absolute;
	        box-sizing: border-box;
	        background-color: white;
	        border-radius: 10px;
   		}
   		.image-container .icon {
		    position: absolute;
		    width: 40px;
		    height: 40px;
		    top: 13px;
		    right: 12px;
		}

	    .image-container img {
	        width: 320px;
	        height: 320px;
	        display: block;
	    }

		/* 이미지 위에 마우스 오버 효과 */
		.image-container:hover img {
			filter : brightness(70%);
		}

		/* 이미지 위에 마우스 오버 효과로 인한 아이콘 opacity 적용 */
		.image-container:hover .icon {
			opacity: 1;
		}
	</style>
</head>
<body>
	<div id="modal" class="modal">
		<div class="modal-content">
			<span class="close-button">&times;</span>
			<!-- 탐색 글자 추가 -->	
			<h2 class="search-text">탐색</h2>	
			<div class="image-row">
		    <div class="image-container" style="left: 619px; top: 244px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image1.jpg" alt="이미지1"></a>
		    </div>

		    <div class="image-container" style="left: 964px; top: 244px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image2.jpg" alt="이미지2"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 1309px; top: 244px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image3.jpg" alt="이미지3"></a>
		    </div>

		    <div class="image-container" style="left: 619px; top: 584px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image4.jpg" alt="이미지4"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 964px; top: 584px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image5.jpg" alt="이미지5"></a>
		    </div>

		    <div class="image-container" style="left: 1309px; top: 584px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image6.jpg" alt="이미지6"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 619px; top: 924px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image7.jpg" alt="이미지7"></a>
		    </div>

		    <div class="image-container" style="left: 964px; top: 924px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image8.jpg" alt="이미지8"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 1309px; top: 924px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image9.jpg" alt="이미지9"></a>
		    </div>

		    <div class="image-container" style="left: 619px; top: 1264px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image10.jpg" alt="이미지10"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 964px; top: 1264px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image11.jpg" alt="이미지11"></a>
		    	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/exploreLinkCopyBtn.png" alt="사진 여러장 있는 게시물">
		    </div>

		    <div class="image-container" style="left: 1309px; top: 1264px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image12.jpg" alt="이미지12"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/videoicon.png" alt="동영상">
		    </div>

		    <div class="image-container" style="left: 619px; top: 1604px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image13.jpg" alt="이미지13"></a>
		    </div>

		    <div class="image-container" style="left: 964px; top: 1604px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image14.jpg" alt="이미지14"></a>
		      	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/videoicon.png" alt="동영상">
		    </div>

		    <div class="image-container" style="left: 1309px; top: 1604px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image15.jpg" alt="이미지15"></a>
		    </div>

		    <div class="image-container" style="left: 619px; top: 1944px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image16.jpg" alt="이미지16"></a>
		    </div>

		    <div class="image-container" style="left: 964px; top: 1944px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image17.jpg" alt="이미지17"></a>
		    	<img class="icon" src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/videoicon.png" alt="동영상">
		    </div>

		    <div class="image-container" style="left: 1309px; top: 1944px;">
				<a href="/profile"><img src="file:///C:/Jsp/sns/sns_spring_project/sns/src/main/resources/static/images/image18.jpg" alt="이미지18"></a>
		    </div>
		  </div>
		</div>
	<script src="/js/quest/quest.js"></script>
</body>
</html>
