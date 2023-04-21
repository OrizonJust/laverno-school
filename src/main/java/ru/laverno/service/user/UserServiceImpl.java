package ru.laverno.service.user;

import org.springframework.stereotype.Service;
import ru.laverno.entitiy.RoleRef;
import ru.laverno.entitiy.User;
import ru.laverno.exception.DataAlreadyExistsException;
import ru.laverno.exception.DataNotFoundException;
import ru.laverno.exception.ParamValidationException;
import ru.laverno.mapper.UserMapper;
import ru.laverno.model.role.RoleRequest;
import ru.laverno.model.user.UserRequest;
import ru.laverno.model.user.UserResponse;
import ru.laverno.repository.UserRepository;
import ru.laverno.service.role.RoleService;
import ru.laverno.utils.Const;
import ru.laverno.utils.ParamValidator;
import ru.laverno.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public UserResponse getUserById(final UUID id) {
        final var user = userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format(Const.USER_NOT_FOUND_EXCEPTION, id)
                ));
        final var roles = roleService.getRolesByUserId(id);


        return UserMapper.userToUserResponse(user, roles);
    }

    @Override
    public UserResponse getUserByEmail(final String email) {
        final var user = userRepository.findUserByEmailIgnoreCase(email).orElseThrow(() ->
                new DataNotFoundException(String.format("Пользователь с email=%s не найден!", email)
                ));
        final var roles = roleService.getRolesByUserId(user.getId());

        return UserMapper.userToUserResponse(user, roles);
    }

    @Override
    public UserResponse getUserByPhone(String phone) {
        final var user = userRepository.findUserByPhone(Utils.convertPhoneIntoFormat(phone)).orElseThrow(() ->
                new DataNotFoundException(String.format("Пользователь с phone=%s не найден!", phone)
                ));
        final var roles = roleService.getRolesByUserId(user.getId());

        return UserMapper.userToUserResponse(user, roles);
    }

    @Override
    public List<UserResponse> getUsersByUsername(final String username) {
        final var users = new ArrayList<User>();
        userRepository.findUsersByUsernameContainsIgnoreCase(username).forEach(users::add);

        return users.stream().map(u -> {
            final var roles = roleService.getRolesByUserId(u.getId());
            return UserMapper.userToUserResponse(u, roles);
        }).toList();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        final var users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);

        return users.stream().map(u -> {
            final var roles = roleService.getRolesByUserId(u.getId());
            return UserMapper.userToUserResponse(u, roles);
        }).toList();
    }

    @Override
    public UserResponse addNewUser(final UserRequest user) throws ParamValidationException, DataAlreadyExistsException {
        ParamValidator.validateEmail(user.email());
        if (user.phone() != null) {
            ParamValidator.validatePhone(user.phone());
        }
        if (userRepository.findUserByEmailIgnoreCase(user.email()).isPresent()) {
            throw new DataAlreadyExistsException(String.format("Пользователь с электронным адресом [email=%s] уже существует в базе!", user.email()));
        }
        if (userRepository.findUserByPhone(user.phone()).isPresent()) {
            throw new DataAlreadyExistsException(String.format("Пользователь с номер телефона [phone=%s] уже существует в базе!", user.phone()));
        }

        final var newUser = new User(
                user.username(),
                user.password(),
                user.email(),
                user.phone() == null ? null : Utils.convertPhoneIntoFormat(user.phone()),
                user.avatar(),
                false,
                Set.of(new RoleRef(roleService.getRoleByName("USER").getId())));

        return UserMapper.userToUserResponse(userRepository.save(newUser), Set.of(roleService.getRoleByName("USER")));
    }

    @Override
    public UserResponse editUser(UserRequest user) {
        if (user.id() != null) {
            if (user.email() != null) {
                ParamValidator.validateEmail(user.email());
            }
            if (user.phone() != null) {
                ParamValidator.validatePhone(user.phone());
            }
            final var userFromDb = userRepository.findById(user.id()).orElseThrow(() ->
                    new DataNotFoundException(String.format(Const.USER_NOT_FOUND_EXCEPTION, user.id())
                    ));
            final var roles = roleService.getRolesByUserId(userFromDb.getId());
            return UserMapper.userToUserResponse(userRepository.save(UserMapper.userRequestToEditedUser(userFromDb, user)), roles);
        } else {
            throw new ParamValidationException("Идентификатор пользователя [id=null] равен NULL!");
        }
    }

    @Override
    public boolean deleteUser(UUID id) {
        if (id == null) {
            throw new ParamValidationException("Идентификатор пользователя [id=null] равен NULL!");
        }

        final var user = userRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(String.format(Const.USER_NOT_FOUND_EXCEPTION, id)
                ));

        userRepository.save(UserMapper.userToDeleteUser(user));
        return true;
    }

    @Override
    public UserResponse addNewRole(RoleRequest roleRequest) throws DataAlreadyExistsException {
        roleService.addNewRole(roleRequest.userId(), roleRequest.roleId());


        final var user = userRepository.findById(roleRequest.userId()).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.USER_NOT_FOUND_EXCEPTION, roleRequest.userId())));
        final var roles = roleService.getRolesByUserId(roleRequest.userId());

        return UserMapper.userToUserResponse(user, roles);
    }

    @Override
    public UserResponse deleteUserRole(RoleRequest roleRequest) throws DataNotFoundException {
        roleService.deleteUserRole(roleRequest.userId(), roleRequest.roleId());

        final var user = userRepository.findById(roleRequest.userId()).orElseThrow(()
                -> new DataNotFoundException(String.format(Const.USER_NOT_FOUND_EXCEPTION, roleRequest.userId())));
        final var roles = roleService.getRolesByUserId(roleRequest.userId());

        return UserMapper.userToUserResponse(user, roles);
    }
}
