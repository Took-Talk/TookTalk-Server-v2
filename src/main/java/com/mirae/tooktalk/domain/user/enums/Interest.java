package com.mirae.tooktalk.domain.user.enums;

public enum Interest {
    BADMINTON("🏸 배드민턴"),
    WINE("🍷 와인"),
    FESTIVAL("🎊 축제"),
    PLANT_CARE("🪴 식물 관리"),
    KONO("🎤 코노"),
    PILATES("🧘 필라테스"),
    POLITICS("📰 정치"),
    RELIGION("🙏 종교"),
    WEIGHT_TRAINING("🏋️ 웨이트 트레이닝"),
    WATCHING_PERFORMANCES("🎬 공연 감상"),
    SONG("🎧 노래"),
    MUSIC("🎶 음악"),
    SWIMMING("🏊 수영"),
    WINTER_SPORTS("🎿 겨울 스포츠"),
    CAMPING("🏕️ 캠핑"),
    SURFING("🏄 서핑"),
    HIKING("🏔️ 등산"),
    BASEBALL("⚾️ 야구"),
    TABLE_TENNIS("🏓 탁구"),
    CLIMBING("🧗 등반"),
    BILLIARDS("🎱 당구"),
    YOGA("🧘 요가"),
    TENNIS("🎾 테니스"),
    BASKETBALL("🏀 농구"),
    SOCCER("⚽️ 축구"),
    GOLF("⛳️ 골프"),
    CURRENT_ISSUES("🗞️ 시사 이슈"),
    WRITING("📝 글쓰기"),
    DANCE("👯 댄스"),
    FASHION("👗 패션"),
    READING("📚 독서"),
    COOKING("🍳 요리"),
    FINANCE("💰 금융"),
    TRAVEL("✈️ 여행"),
    SELF_DEVELOPMENT("🤔 자기계발"),
    WALKING("🚶 걷기"),
    DRAWING("🖌️ 그림 그리기"),
    PHOTO_SHOOT("📷 사진 촬영"),
    PLAY("🎭 연극"),
    EXHIBITION_VIEWING("🎆 전시회 관람"),
    CAFE_TOUR("☕️ 카페 투어"),
    PLAYING_MUSICAL_INSTRUMENT("🎹 악기 연주"),
    GAME("🎮 게임"),
    IT("💻 IT"),
    MOVIE("🎬 영화"),
    BICYCLE("🚲 자전거"),
    ANIMATION("🎞️ 애니메이션"),
    INTERIOR_DESIGN("🏠 인테리어 디자인"),
    GOURMET_TOUR("🍚 맛집 투어"),
    DRIVE("🏎️ 드라이브"),
    FOREIGN_LANGUAGE("🗣️ 외국어/언어 공부");

    private String emoji;

    private Interest(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
