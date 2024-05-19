<template>
  <v-container>
    <v-list>
      <v-subheader>댓글</v-subheader>
      <v-list-item-group>
        <v-list-item v-for="comment in comments" :key="comment.id">
          <v-avatar size="36px">
            <v-img :src="comment.avatar"></v-img>
          </v-avatar>
          <v-list-item-content>
            <v-list-item-title v-text="comment.author"></v-list-item-title>
            <v-list-item-subtitle
              v-text="comment.content"
            ></v-list-item-subtitle>
            <v-list-item-subtitle>{{ comment.time }}</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </v-list-item-group>
    </v-list>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      comments: [
        {
          id: 1,
          author: "사용자1",
          content: "정말 멋진 글입니다!",
          time: "2시간 전",
          avatar: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460",
        },
        {
          id: 2,
          author: "사용자2",
          content: "동의해요!",
          time: "1시간 전",
          avatar: "https://avatars0.githubusercontent.com/u/9064066?v=4&s=460",
        },
      ],
    };
  },
};
</script>
