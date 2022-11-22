document.addEventListener('DOMContentLoaded', function(){
    //별점선택 이벤트 리스너
    document.querySelector('.rating').addEventListener('click',function(e){
        let elem = e.target;
        if(elem.classList.contains('rate_radio')){
            rating.setRate(parseInt(elem.value));
        }
    })
    //제목 글자수 초과 체크
    document.querySelector('.review_t_textarea').addEventListener('keydown',function(){
        //리뷰 40자 초과 안되게 자동 자름
        let review_t = document.querySelector('.review_t_textarea');
        let lengthCheckEx = /^.{40,}$/;
        if(lengthCheckEx.test(review_t.value)){
            //40자 초과 컷
            review_t.value = review_t.value.substr(0,40);
        }
    });
    //리뷰 글자수 초과 체크
    document.querySelector('.review_textarea').addEventListener('keydown',function(){
        //리뷰 400자 초과 안되게 자동 자름
        let review = document.querySelector('.review_textarea');
        let lengthCheckEx = /^.{400,}$/;
        if(lengthCheckEx.test(review.value)){
            //400자 초과 컷
            review.value = review.value.substr(0,400);
        }
    });

    //저장 전송전 체크
    document.querySelector('#save').addEventListener('click', function(e){
        //별점 선택 안했으면 메시지 표시
        if(rating.rate === 0){
            rating.showMessage('rate');
            return false;
        }
        //제목 1글자 미만이면 메시지 표시
        if(document.querySelector('.review_t_textarea').value.length < 1){
            rating.showMessage('review_t');
            return false;
        }
        //리뷰 1글자 미만이면 메시지 표시
        if(document.querySelector('.review_textarea').value.length < 1){
            rating.showMessage('review');
            return false;
        }
        //폼 초기화
        alert(`별점: ${rating.rate}\n제목: ${document.querySelector('.review_t_textarea').value}\n내용: ${document.querySelector('.review_textarea').value}`);
        var gymId = localStorage.getItem('gym');
        var userId = localStorage.getItem('userName');
        var form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action','/starsave')
        document.body.appendChild(form);
        var input = document.createElement('input');
        input.setAttribute('type','hidden')
        input.setAttribute('name', 'star');
        input.setAttribute('value',rating.rate);
        form.appendChild(input);
        input = document.createElement('input');
        input.setAttribute('type','hidden')
        input.setAttribute('name', 'reviews');
        input.setAttribute('value',document.querySelector('.review_textarea').value);
        form.appendChild(input);
        input = document.createElement('input');
        input.setAttribute('type','hidden');
        input.setAttribute('name','gym');
        input.setAttribute('value',gymId);
        form.appendChild(input);
        input = document.createElement('input');
        input.setAttribute('type','hidden');
        input.setAttribute('name','id');
        input.setAttribute('value',userId);
        form.appendChild(input);
        form.submit();
        rating.setRate(0);
        document.querySelector('.review_t_textarea').value = '';
        document.querySelector('.review_textarea').value = '';
    });
});


//별점 마킹 모듈 프로토타입으로 생성
function Rating(){};
Rating.prototype.rate = 0;
Rating.prototype.setRate = function(newrate){
    //별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
    this.rate = newrate;
    //document.querySelector('.ratefill').style.width = parseInt(newrate * 60) + 'px';
    let items = document.querySelectorAll('.rate_radio');
    items.forEach(function(item, idx){
        if(idx < newrate){
            item.checked = true;
        }else{
            item.checked = false;
        }
    });
}
Rating.prototype.showMessage = function(type){//경고메시지 표시
    switch(type){
        case 'rate':
            //안내메시지 표시
            document.querySelector('.review_rating .warning_msg').style.display = 'block';
            //지정된 시간 후 안내 메시지 감춤
            setTimeout(function(){
                document.querySelector('.review_rating .warning_msg').style.display = 'none';
            },1000);
            break;
        case 'review_t':
            //안내메시지 표시
            document.querySelector('.review_t .warning_msg').style.display = 'block';
            //지정된 시간 후 안내 메시지 감춤
            setTimeout(function(){
                document.querySelector('.review_t .warning_msg').style.display = 'none';
            },1000);
            break;
        case 'review':
            //안내메시지 표시
            document.querySelector('.review_contents .warning_msg').style.display = 'block';
            //지정된 시간 후 안내 메시지 감춤
            setTimeout(function(){
                document.querySelector('.review_contents .warning_msg').style.display = 'none';
            },1000);
            break;
    }
}

let rating = new Rating();//별점 인스턴스 생성