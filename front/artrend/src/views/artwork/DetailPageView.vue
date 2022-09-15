<template>
  <div>
    <div id="background-div"></div>
    <div id="img-sticky">
      <div id="img-wrap2">
        <img
          src="@/assets/main-img/1.jpg"
          alt="img"
          id="selected-img2"
          @click="getImageModal"
        />
        <div id="myModal" class="modal">
          <img
            class="modal-content"
            id="img01"
            alt="Image"
            @click="outImageModal"
          />
        </div>
      </div>
      <!-- <div id="img-wrap">
        <img src="@/assets/main-img/1.jpg" alt="img" id="selected-img" />
      </div> -->
      <div id="img-description1">
        <div id="img-title-wrap">
          <h1 id="img-title">Boby Notre-Dame</h1>
          <h5 id="img-title2">한글제목다그닥다그닥, 1985</h5>
          <h4 id="img-title3">Andy worhol</h4>
          <hr />
          <div>
            <span id="description-cell">Place</span>
            <span id="description-inner"
              >Saint-Remy-de-Provence(Object made in)
            </span>
          </div>
          <div>
            <span id="description-cell">Date</span>
            <span id="description-inner">1989</span>
          </div>
          <div>
            <span id="description-cell">Oil on Canvas</span>
            <span id="description-inner"
              >Saint-Remy-de-Provence(Object made in)
            </span>
          </div>
          <div>
            <span id="description-cell">Dimensions</span>
            <span id="description-inner">73.6 x 92.3 cm (29 x 36 5/8 in)</span>
          </div>
          <div id="detail-description">
            <sapn id="description-cell">Description</sapn>
            <p id="description-inner2">
              국회는 헌법개정안이 공고된 날로부터 60일 이내에 의결하여야 하며,
              국회의 의결은 재적의원 3분의 2 이상의 찬성을 얻어야 한다. 국가는
              지역간의 균형있는 발전을 위하여 지역경제를 육성할 의무를 진다.
              근로자는 근로조건의 향상을 위하여 자주적인 단결권·단체교섭권 및
              단체행동권을 가진다. 국가는 전통문화의 계승·발전과 민족문화의
              창달에 노력하여야 한다. 누구든지 병역의무의 이행으로 인하여
              불이익한 처우를 받지 아니한다. 재의의 요구가 있을 때에는 국회는
              재의에 붙이고, 재적의원과반수의 출석과 출석의원 3분의 2 이상의
              찬성으로 전과 같은 의결을 하면 그 법률안은 법률로서 확정된다.
              대통령은 필요하다고 인정할 때에는 외교·국방·통일 기타 국가안위에
              관한 중요정책을 국민투표에 붙일 수 있다. 모든 국민은 소급입법에
              의하여 참정권의 제한을 받거나 재산권을 박탈당하지 아니한다.
              대통령의 임기연장 또는 중임변경을 위한 헌법개정은 그 헌법개정 제안
              당시의 대통령에 대하여는 효력이 없다. 누구든지 체포 또는 구속의
              이유와 변호인의 조력을 받을 권리가 있음을 고지받지 아니하고는 체포
              또는 구속을 당하지 아니한다. 체포 또는 구속을 당한 자의 가족등
              법률이 정하는 자에게는 그 이유와 일시·장소가 지체없이 통지되어야
              한다. 제안된 헌법개정안은 대통령이 20일 이상의 기간 이를
              공고하여야 한다. 모든 국민은 행위시의 법률에 의하여 범죄를
              구성하지 아니하는 행위로 소추되지 아니하며, 동일한 범죄에 대하여
              거듭 처벌받지 아니한다. 법원은 최고법원인 대법원과 각급법원으로
              조직된다. 국회의원은 법률이 정하는 직을 겸할 수 없다. 모든 국민은
              헌법과 법률이 정한 법관에 의하여 법률에 의한 재판을 받을 권리를
              가진다. 원장은 국회의 동의를 얻어 대통령이 임명하고, 그 임기는
              4년으로 하며, 1차에 한하여 중임할 수 있다. 군인 또는 군무원이 아닌
              국민은 대한민국의 영역안에서는 중대한 군사상
              기밀·초병·초소·유독음식물공급·포로·군용물에 관한 죄중 법률이 정한
              경우와 비상계엄이 선포된 경우를 제외하고는 군사법원의 재판을 받지
              아니한다. 대한민국은 국제평화의 유지에 노력하고 침략적 전쟁을
              부인한다. 모든 국민은 사생활의 비밀과 자유를 침해받지 아니한다.
              제2항과 제3항의 처분에 대하여는 법원에 제소할 수 없다. 정당의
              설립은 자유이며, 복수정당제는 보장된다. 국무회의는 정부의 권한에
              속하는 중요한 정책을 심의한다. 모든 국민은 자기의 행위가 아닌
              친족의 행위로 인하여 불이익한 처우를 받지 아니한다. 정부는 예산에
              변경을 가할 필요가 있을 때에는 추가경정예산안을 편성하여 국회에
              제출할 수 있다. 법률안에 이의가 있을 때에는 대통령은 제1항의
              기간내에 이의서를 붙여 국회로 환부하고, 그 재의를 요구할 수 있다.
              국회의 폐회중에도 또한 같다. 대통령은 국가의 원수이며, 외국에
              대하여 국가를 대표한다. 대통령은 제4항과 제5항의 규정에 의하여
              확정된 법률을 지체없이 공포하여야 한다. 제5항에 의하여 법률이
              확정된 후 또는 제4항에 의한 확정법률이 정부에 이송된 후 5일 이내에
              대통령이 공포하지 아니할 때에는 국회의장이 이를 공포한다. 이
              헌법중 공무원의 임기 또는 중임제한에 관한 규정은 이 헌법에 의하여
              그 공무원이 최초로 선출 또는 임명된 때로부터 적용한다. 대통령은
              조약을 체결·비준하고, 외교사절을 신임·접수 또는 파견하며,
              선전포고와 강화를 한다. 대통령은 국회에 출석하여 발언하거나
              서한으로 의견을 표시할 수 있다. 국무위원은 국정에 관하여 대통령을
              보좌하며, 국무회의의 구성원으로서 국정을 심의한다. 형사피해자는
              법률이 정하는 바에 의하여 당해 사건의 재판절차에서 진술할 수 있다.
              대통령이 제1항의 기간내에 공포나 재의의 요구를 하지 아니한 때에도
              그 법률안은 법률로서 확정된다. 중앙선거관리위원회는 대통령이
              임명하는 3인, 국회에서 선출하는 3인과 대법원장이 지명하는 3인의
              위원으로 구성한다. 위원장은 위원중에서 호선한다. 대한민국은
              민주공화국이다. 근로조건의 기준은 인간의 존엄성을 보장하도록
              법률로 정한다. 국회의원은 국가이익을 우선하여 양심에 따라 직무를
              행한다.
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainPageView',
  setup() {
    const getImageModal = function () {
      const modal = document.getElementById('myModal')
      const temp = document.getElementById('selected-img2').src
      console.log(temp)
      const img = require('../../assets/main-img/1.jpg')
      console.log(img)
      const modalImg = document.getElementById('img01')
      modal.style.display = 'block'
      modalImg.src = this.img
    }
    const outImageModal = function () {
      const modalImg = document.getElementById('img01')
      modalImg.className += 'out'
      setTimeout(function () {
        const modal = document.getElementById('myModal')
        modal.style.display = 'none'
        modalImg.className = 'modal-content'
      }, 400)
    }
    return {
      getImageModal,
      outImageModal,
    }
  },
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Serif+Georgian&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;400&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans:wght@100;200;500;600&display=swap');
#description-cell {
  font-size: 35px;
  font-family: 'Noto Sans', sans-serif;
  font-weight: 500;
  display: inline-block;
  width: 300px;
  margin-top: 50px;
}
#description-inner2 {
  margin-top: 20px;
  font-size: 23px;
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 100;
}
#description-inner {
  font-size: 23px;
  font-family: 'Noto Sans', sans-serif;
  font-weight: 200;
}
hr {
  background-color: white;
  width: 100px;
  height: 2px;
  margin-top: 50px;
}
#img-title2 {
  font-family: 'Noto Sans KR', sans-serif;
  font-weight: 400;
}
#img-title3 {
  font-family: 'Noto Serif Georgian', serif;
}
#img-title {
  font-size: 70px;
  font-family: 'Noto Serif Georgian', serif;
  margin-bottom: 50px;
}
#img-title-wrap {
  margin-left: 10%;
}
#img-description1 {
  width: 60%;
  position: relative;
  top: 200px;
  left: 700px;
  color: white;
}
#img-sticky {
  display: flex;
}
#selected-img {
  height: 100%;
  position: relative;
  animation: slideInRight 2s ease-out;
}
#img-wrap {
  height: 800px;
  text-align: end;
  overflow: hidden;
  position: relative;
  top: 200px;
  width: 30vw;
}
#selected-img2 {
  height: 100%;
  position: relative;
  animation: slideInRight 2s ease-out;
  cursor: pointer;
}
#img-wrap2 {
  height: 800px;
  text-align: end;
  overflow: hidden;
  position: fixed;
  top: 200px;
  width: 30vw;
}
#background-div {
  margin-left: 20%;
  width: 80%;
  background-color: rgb(0, 0, 0);
  height: 300vh;
  animation: fadeInRight 1s ease-out;
  position: absolute;
  z-index: -1;
}
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 100px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.9); /* Black w/ opacity */
}

/* Modal Content (image) */
.modal-content {
  margin: auto;
  display: block;
  width: 75%;
  //max-width: 75%;
}

/* Caption of Modal Image */
#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  text-align: center;
  color: #ccc;
  padding: 10px 0;
  height: 150px;
}

/* Add Animation */
.modal-content,
#caption {
  -webkit-animation-name: zoom;
  -webkit-animation-duration: 0.6s;
  animation-name: zoom;
  animation-duration: 0.6s;
}

.out {
  animation-name: zoom-out;
  animation-duration: 0.6s;
}

@-webkit-keyframes zoom {
  from {
    -webkit-transform: scale(1);
  }
  to {
    -webkit-transform: scale(2);
  }
}

@keyframes zoom {
  from {
    transform: scale(0.4);
  }
  to {
    transform: scale(1);
  }
}

@keyframes zoom-out {
  from {
    transform: scale(1);
  }
  to {
    transform: scale(0);
  }
}

/* The Close Button */
.close {
  position: absolute;
  top: 15px;
  right: 35px;
  color: #f1f1f1;
  font-size: 40px;
  font-weight: bold;
  transition: 0.3s;
}

.close:hover,
.close:focus {
  color: #bbb;
  text-decoration: none;
  cursor: pointer;
}

/* 100% Image Width on Smaller Screens */
@media only screen and (max-width: 700px) {
  .modal-content {
    width: 100%;
  }
}
</style>
