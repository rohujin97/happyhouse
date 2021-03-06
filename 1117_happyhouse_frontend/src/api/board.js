import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteAllComment(boardno, success, fail) {
  api.delete(`/board/all/${boardno}`).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

function searchArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

function totalArticle(param, success, fail) {
  api.get(`/board/total`, { params: param }).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/board/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function listComment(commentno, success, fail) {
  api.get(`/board/comment/${commentno}`).then(success).catch(fail);
}

function deleteComment(commentno, success, fail) {
  api.delete(`/board/comment/${commentno}`).then(success).catch(fail);
}

function topArticle(success, fail) {
  api.get(`/board/top`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteAllComment,
  deleteArticle,
  searchArticle,
  totalArticle,
  writeComment,
  listComment,
  deleteComment,
  topArticle,
};
