




/*셀렉트 박스*/
let select_title = $(".selectBox > button"),
    select_arrow = $(".historyList > span.icoArrow");

select_title.click(function () {
    //    $(this).parent('.selectBox').toggleClass("active");
    if ($('.selectBox').hasClass('active')) {
        $('.selectBox').removeClass('active');
    } else {
        $(this).parent('.selectBox').addClass("active");
    }
});

select_arrow.click(function () {
    $(this).parent('.selectBox').toggleClass("active");
});


$(".optionList > li").on('click', function () {
    let li_value = $(this).text();
    $(this).parent(".optionList").siblings('.label').text(li_value);
    $(this).parents('.selectBox').removeClass("active");
});
$("body").click(function (e) {
    if ($(".selectBox").hasClass("active")) {
        if (!$(".selectBox").has(e.target).length) {
            $(".selectBox").removeClass("active");
        };
    }
});






/*모달*/
$('.close-btn, .modal-bg').click(function () {
    $('.modal').css('display', 'none');
    event.preventDefault();
});


/*마이페이지 메뉴*/
$('.mypage-btn').click(function(){
   $('.mypageMenu-box').toggleClass('actived') 
});

$('.mypageMenu-box li a').click(function(){
   $('.mypageMenu-box').removeClass('acitved'); 
});


/*모바일 메뉴*/
$('.headerWrap .menuBtn').click(function(){
   $('.m-menuWrap').addClass('actived');
});

$('.m-menuWrap .close-btn').click(function(){
   $('.m-menuWrap').removeClass('actived');
});



/*체험장등록 diy.html*/
$('.diyWrap .timetableWrap div .btnWrap .reWrite').click(function(){
   $(this).parent('.btnWrap').parent('div').addClass('update');
    $(this).parent('.btnWrap').parent('div').find('input').prop('readonly', false);
    event.preventDefault();
});

$('.diyWrap .timetableWrap div .btnWrap .save').click(function(){
   $(this).parent('.btnWrap').parent('div').removeClass('update');
   $(this).parent('.btnWrap').parent('div').find('input').prop('readonly', true);
    event.preventDefault();
});

$('.diyWrap .timetableWrap .titleWrap .update-btn').click(function(){
   if($(this).hasClass('actived')){
       $(this).removeClass('actived');
       $(this).text('전체수정');
       $(this).parents('.timetableWrap').find('.tableList').removeClass('update');
       $(this).parents('.timetableWrap').children('.tableList').find('input').prop('readonly', true);
       
       event.preventDefault();
       
      }else{
          $(this).addClass('actived')
          $(this).text('전체저장');
          $(this).parents('.timetableWrap').find('.tableList').addClass('update');
          
          $(this).parents('.timetableWrap').children('.tableList').find('input').prop('readonly', false);
          event.preventDefault();
      }
});




/*푸터 모달*/
$('.footerModal1-btn').click(function(){
   $('.footerModal1').css('display','block');
    event.preventDefault();
});



/*체험장 등록 예상견적서 .diy2.html*/

//수량 클릭
$('.diyWrap .numberSel-wrap .selectWrap > li').click(function(){
    $('.diyWrap .numberSel-wrap .selectWrap > li').removeClass('actived');
   $(this).addClass('actived'); 
    event.preventDefault();
});

$('.countSave-btn').click(function(){
   $('.diyWrap .numberSel-wrap .selectWrap > li').removeClass('actived');
    event.preventDefault();
});



//수량 옵션
        $('.countWrap :button').on({
            'click' : function(e){
                e.preventDefault();
                var $count = $(this).parent('.countWrap').find('.inp');
                var now = parseInt($count.val());
                var min = 0;
                var max = 999;
                var num = now;
                if($(this).hasClass('minus')){
                    var type = 'm';
                }else{
                    var type = 'p';
                }
                if(type=='m'){
                    if(now>min){
                        num = now - 1;
                    }
                }else{
                    if(now<max){
                        num = now + 1;
                    }
                }
                if(num != now){
                    $count.val(num);
                }
            }
});






/*tab*/
$('.tabWrap > li > a').click(function () {
    let contId = $(this).attr('href');
    $('.tabWrap > li > a').removeClass('actived');
    $(this).addClass('actived');
    $('.tabCont').removeClass('actived');

    $(contId).addClass('actived');

    event.preventDefault();
});

/*차량문의 inquiry2*/
//경유지 추가
$('.inquiry2-wrap .titWrap .addBtn').click(function(){
   if($(this).hasClass('actived')){
       $(this).removeClass('actived');
       $(this).children('p').text('경유지 추가');
       $(this).parent('.titWrap').parent('.tableList').find('textarea').css('display','none');
       $(this).parents('tr').find('textarea').css('display','none');
   }else{
       $(this).addClass('actived');
       $(this).children('p').text('경유지 삭제');
       $(this).parent('.titWrap').parent('.tableList').find('textarea').css('display','block');
       $(this).parents('tr').find('textarea').css('display','block');
   }
    event.preventDefault();
});



//기사님 동행여부
$('.inquiry2-wrap .selectTab li a.driver1').click(function(){
    $(this).addClass('actived');
    $('.inquiry2-wrap .selectTab li a.driver2').removeClass('actived');
    $('.inquiry2-wrap .tableList.driver2-text').css('display','none');
    
    event.preventDefault();
})

$('.inquiry2-wrap .selectTab li a.driver2').click(function(){
    $(this).addClass('actived');
    $('.inquiry2-wrap .selectTab li a.driver1').removeClass('actived');
    $('.inquiry2-wrap .tableList.driver2-text').css('display','block');
    
    event.preventDefault();
})


//차량문의_상세 기사님 동행여부
$('.inquiryUpdate-wrap table td .driver2').click(function(){
   if ($(this).is(":checked")) {
       $('.carText2').css('display','none')
        
    } else {
        $('.carText2').css('display','revert') 
    }
});

$('.inquiryUpdate-wrap table td .driver1').click(function(){
   if ($(this).is(":checked")) {
       $('.carText2').css('display','revert')
        
    } else {
        $('.carText2').css('display','none') 
    }
});



//선호버스

$('.inquiry1-wrap .tableList .checkWrap2 a').click(function(){
   $(this).toggleClass('actived'); 
    event.preventDefault();
});


$('.inquiry1-wrap .checkWrap5 div a.check').click(function(){
    $('.inquiry1-wrap .checkWrap5 div a.noneCheck').removeClass('actived');
    
    $(this).toggleClass('actived');

     event.preventDefault();
});

$('.inquiry1-wrap .checkWrap5 div a.noneCheck').click(function(){
    $('.inquiry1-wrap .checkWrap5 div a.check').removeClass('actived');
    
    $(this).toggleClass('actived');

     event.preventDefault();
});

//차량문의_상세 선호버스
$('.inquiryUpdate-wrap table td .noneCheck').click(function(){
   if ($(this).children('input').is(":checked")) {
        $(this).siblings('.check').children('input').prop("checked", false);
    }
});

$('.inquiryUpdate-wrap table td .check').click(function(){
   if ($(this).children('input').is(":checked")) {
        $(this).siblings('.noneCheck').children('input').prop("checked", false);
    }
});







/*여행자보험 inquiry3.html*/
//여행선택
$('.inquiry3-wrap .tableList .tripSel li.trip1').click(function(){
    $(this).addClass('actived');
    $('.inquiry3-wrap .tableList .tripSel li.trip2').removeClass('actived');
    
    $('.inquiry3-wrap .trip1-text').addClass('actived');
    $('.inquiry3-wrap .trip2-text').removeClass('actived');
})
$('.inquiry3-wrap .tableList .tripSel li.trip2').click(function(){
    $(this).addClass('actived');
    $('.inquiry3-wrap .tableList .tripSel li.trip1').removeClass('actived');
    
    $('.inquiry3-wrap .trip2-text').addClass('actived');
    $('.inquiry3-wrap .trip1-text').removeClass('actived');
})


$('.inquiry3-wrap .tableList .tripChk li').click(function(){
   $('.inquiry3-wrap .tableList .tripChk li').removeClass('actived');
    $(this).addClass('actived');
});


//수정페이지 - 여행선택
$('.inquiryUpdate-wrap table td .trip1').click(function(){
   $('.trip1-text').addClass('actived');
   $('.trip2-text').removeClass('actived');
});
$('.inquiryUpdate-wrap table td .trip2').click(function(){
   $('.trip2-text').addClass('actived');
   $('.trip1-text').removeClass('actived');
});


/*비회원작성시*/
    $('.nUser-btn').click(function(){
       $('.inquiry1-wrap .tableList.nUser').css('display','block'); 
       $('.inquiry3-wrap .tableList.loginUser').css('display','none'); 
    });



/*견학신청 예약모달*/
$(window).resize(function(){ 
    if (window.innerWidth > 1024) {  

    $('.reserve-btn').click(function(){
       $('.pc-reservModal').css('display','block'); 
       $('.m-reservModal').css('display','none'); 

        event.preventDefault();
    });

    } else {

    $('.reserve-btn').click(function(){
       $('.m-reservModal').css('display','block'); 
       $('.pc-reservModal').css('display','none'); 

        event.preventDefault();
    });

    }

}).resize(); 









/*datepicker*/
$("#datepicker, #datepicker1, #datepicker2, #datepicker3, #datepicker4").datepicker({
    language: 'ko'
});

datePickerSet($("#datepickerStr"), $("#datepickerFin"), true); 
//다중은 시작하는 달력 먼저, 끝달력 2번째

/*
    * 달력 생성기
    * @param sDate 파라미터만 넣으면 1개짜리 달력 생성
    * @example   datePickerSet($("#datepicker"));
    * 
    * 
    * @param sDate, 
    * @param eDate 2개 넣으면 연결달력 생성되어 서로의 날짜를 넘어가지 않음
    * @example   datePickerSet($("#datepickerStr"), $("#datepickerFin"));
    */
function datePickerSet(sDate, eDate, flag) {

    //시작 ~ 종료 2개 짜리 달력 datepicker	
    if (!isValidStr(sDate) && !isValidStr(eDate) && sDate.length > 0 && eDate.length > 0) {
        var sDay = sDate.val();
        var eDay = eDate.val();

        if (flag && !isValidStr(sDay) && !isValidStr(eDay)) { //처음 입력 날짜 설정, update...			
            var sdp = sDate.datepicker().data("datepicker");
            sdp.selectDate(new Date(sDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요

            var edp = eDate.datepicker().data("datepicker");
            edp.selectDate(new Date(eDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
        }

        //시작일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        if (!isValidStr(eDay)) {
            sDate.datepicker({
                maxDate: new Date(eDay.replace(/-/g, "/"))
            });
        }
        sDate.datepicker({
            language: 'ko',
            autoClose: true,
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });

        //종료일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        if (!isValidStr(sDay)) {
            eDate.datepicker({
                minDate: new Date(sDay.replace(/-/g, "/"))
            });
        }
        eDate.datepicker({
            language: 'ko',
            autoClose: true,
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });

        //한개짜리 달력 datepicker
    } else if (!isValidStr(sDate)) {
        var sDay = sDate.val();
        if (flag && !isValidStr(sDay)) { //처음 입력 날짜 설정, update...			
            var sdp = sDate.datepicker().data("datepicker");
            sdp.selectDate(new Date(sDay.replace(/-/g, "/"))); //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
        }

        sDate.datepicker({
            language: 'ko',
            autoClose: true
        });
    }


    function isValidStr(str) {
        if (str == null || str == undefined || str == "")
            return true;
        else
            return false;
    }
}

// 천단위 콤마
function fcomma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

// 천단위 콤마 제거
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

function fConvertHour(second) {
    let hour = parseInt((second % 3600) / 60) < 10 ? '0' + parseInt((second % 3600) / 60) : parseInt((second % 3600) / 60);

    return hour;
}
function fConvertMin(second) {
    let min = parseInt(second % 60) < 10 ? '0' + parseInt(second % 60) : parseInt(second % 60);

    return min;
}

function topmaxLengthCheck(object, length){
    if (object.value.length > length){
        object.value = object.value.slice(0, length);
    }
}

function toastModal(txt, sec) {
    let secs = 1000;
    if(sec == undefined || sec == null) secs = 1000;
    else secs = sec;

    const newDiv = document.createElement('div');
    const newText = document.createTextNode(txt);

    newDiv.appendChild(newText);

    $(newDiv).css({
        "position":"fixed",
        "top":"50%",
        "left":"50%",
        "width":"fit-content",
        "max-width":"calc(100% - 60vw)",
        "transform":"translateX(-50%)",
        "backgroundColor":"rgba(43, 53, 53, 0.7)",
        "backdrop-filter":"blur(10px)",
        // "box-shadow":"0px 0px 20px rgb(43 53 53 / 10%)",
        "padding":"1rem 30px",
        "border-radius":"10px",
        "color":"var(--white)",
        "font-size":"1rem",
        "z-index":"9999999",
        "text-align":"center",
        "line-height":"1.5rem",
        "font-weight":"500"
    });
    // $(newDiv).css({"position":"fixed","top":"49%","left":"50%","transform":"translateX(-50%)","background-color":"#276cad","padding":"12px 30px","border-radius":"20px","color":"#fff","z-index":9999});

    document.body.appendChild(newDiv);

    setTimeout(function () {
        $(newDiv).animate({
            opacity: "hide"
        });
    }, secs);
}






























