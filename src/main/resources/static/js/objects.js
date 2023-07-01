class PersonInfoDto {
    constructor(props) {
        if (props == null) {
            return;
        }

        this.score = props.score;
        this.rank = props.rank;
        this.xuanKao = [];
        this.xuanKao = props.xuanKao;

        this.minRank = props.minRank;
        this.maxRank = props.maxRank;

        this.xuanKaoCombinations = [];
        this.xuanKaoCombinations = props.xuanKaoCombinations;
        this.xuanKaoChooseType = 0;
        this.maxFee = props.maxFee;
        this.minFee = props.minFee;
        this.minCount = props.minCount;
        this.maxCount = props.maxCount;

        this.regionList = [[]];
        this.regionList = props.regionList;
    }
}

/**
 * ZhiyuanDto 的 JavaScript 类
 */
class ZhiyuanDto {
    constructor(props) {
        if (props == null) {
            return;
        }

        this.klType = props.klType;
        this.lcType = props.lcType;
        this.schoolId = props.schoolId;
        this.schoolName = props.schoolName;
        this.fee = props.fee;
        this.zyId = props.zyId;
        this.zyName = props.zyName;
        this.province = props.province;
        this.city = props.city;
        this.xuanKao = props.xuanKao;
        this.count = props.count;
        this.msg = props.msg;
        this.recentYearStatusList = props.recentYearStatusList.map(e => new RecentYearStatusDto(e));
    }
}

/**
 * RecentYearStatusDto 的 JavaScript 类
 */
class RecentYearStatusDto {
    constructor(props) {
        if (props == null) {
            return;
        }

        this.count = props.count;
        this.rank = props.rank;
        this.score = props.score;
        this.match = props.match;
    }
}