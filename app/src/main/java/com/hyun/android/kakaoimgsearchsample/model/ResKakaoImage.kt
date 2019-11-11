package com.hyun.android.kakaoimgsearchsample.model

data class ResKakaoImage(var documents: ArrayList<ResKakaoImageDoc>, var meta: ResKakaoImageMeta)
/**
collection	        String  컬렉션
thumbnail_url	   	String  이미지 썸네일 URL
image_url	        String  이미지 URL
width	           	Integer 이미지의 가로 크기
height	           	Integer 이미지의 세로 크기
display_sitename    String  출처명
doc_url     	    String  문서 URL
datetime	        String  문서 작성시간. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
 */
data class ResKakaoImageDoc(
        var collection: String,
        var thumbnail_url: String,
        var image_url: String,
        var width: Int,
        var height: Int,
        var display_sitename: String,
        var doc_url: String,
        var datetime: String
)

/**
 *
query	String  검색을 원하는 질의어. 특정 블로그 글만 검색하고 싶은 경우 블로그 url과 검색어를 ' '(공백) 구분자로 넣을 수 있음.	O
sort	String  결과 문서 정렬 방식	X (accuracy)	accuracy (정확도순) or recency (최신순)
page	Integer 결과 페이지 번호	X(기본 1)	1-50 사이
size	Integer 한 페이지에 보여질 문서의 개수	X(기본 10)	1-50 사이
 */
data class ResKakaoImageMeta(var query: String, var sort: String, var page: Int, var size: Int)