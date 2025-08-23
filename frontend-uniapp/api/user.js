import request from '@/utils/request.js';

export function fetchUserPage({ page, size, kw }) {
  return request({
    url: '/api/user/list',
    method: 'GET',
    data: { page, size, kw }
  });
}
