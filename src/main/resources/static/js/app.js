let app = new Vue({
    el: '#app',
    data: {
        personInfoDto: new PersonInfoDto({"xuanKaoChooseType": 0}),
        xuanKaoCheckList: [["", false], ["", false], ["", false], ["", false], ["政治", false], ["历史", false], ["地理", false], ["物理", false], ["化学", false], ["生物", false], ["技术", false]],
        maxSelection: 3,
        zhiyuanDtoList: [],

    },
    created() {
    },
    methods: {
        recommend() {
            this.zhiyuanDtoList = [];
            let url = "recommend";
            let p = this.personInfoDto;
            if (!p.rank) {
                alert("请输入位次");
                return;
            }

            p.xuanKao = [];
            for (let i = 0; i < this.xuanKaoCheckList.length; i++) {
                if (this.xuanKaoCheckList[i][0] && this.xuanKaoCheckList[i][1]) {
                    p.xuanKao.push(i === 10 ? 0 : i);
                }
            }
            axios.post(url, p).then((res) => {
                let result = res.data.result;
                if (!result) {
                    return;
                }

                for (let i = 0; i < result.length; i++) {
                    this.zhiyuanDtoList.push(new ZhiyuanDto(result[i]));
                }
                console.log(this.zhiyuanDtoList);
            });
        },
        isCheckedDisabled(index) {
            const selectedCount = this.xuanKaoCheckList.filter(item => item[1]).length;
            return selectedCount >= this.maxSelection && !this.xuanKaoCheckList[index][1];
        },
        displayXuanKaoStr(xuanKao) {
            let dot = ",";
            let and = "";
            let str = xuanKao.join(dot);
            if (str.indexOf(dot) < 0 && str.length > 1) {
                and = "且";
            }
            let res = "";
            for (let i = 0; i < str.length; i++) {
                let s = str[i];
                if (s === dot) {
                    res += dot;
                } else {
                    s = parseInt(s);
                    if (s === 0) {
                        s = 10;
                    }
                    res += this.xuanKaoCheckList[s][0] + and;
                }
            }
            if (res.endsWith(and)) {
                res = res.substring(0, res.length - and.length);
            }
            if (!res) {
                res = "无";
            }
            return res.replaceAll(dot, "或");
        },
    }
});